package com.postread.streak.service;

import com.postread.shared.exception.BusinessException;
import com.postread.shared.exception.ResourceNotFoundException;
import com.postread.streak.dto.CreateStreakRequest;
import com.postread.streak.model.Streak;
import com.postread.streak.model.Streak.StreakStatus;
import com.postread.streak.model.StreakActivity;
import com.postread.streak.repository.StreakActivityRepository;
import com.postread.streak.repository.StreakRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;
    private final StreakActivityRepository streakActivityRepository;

    public Streak createStreak(String creatorId, CreateStreakRequest request) {
        List<String> participants = new ArrayList<>(
                request.participantIds() != null ? request.participantIds() : List.of()
        );
        if (!participants.contains(creatorId)) {
            participants.add(0, creatorId);
        }

        // Save streak with currentStreak=0 — only counts after real reading activity
        return streakRepository.save(Streak.builder()
                .title(request.title())
                .creatorId(creatorId)
                .participantIds(participants)
                .startDate(LocalDate.now())
                .goalType(request.goalType())
                .goalValue(request.goalValue())
                .currentStreak(0)
                .bestStreak(0)
                .status(StreakStatus.ACTIVE)
                .build());
    }

    public List<Streak> getMyActiveStreaks(String userId) {
        return streakRepository.findActiveByParticipant(userId);
    }

    public Streak getStreak(String streakId) {
        return streakRepository.findById(streakId)
                .orElseThrow(() -> new ResourceNotFoundException("Streak", streakId));
    }

    public void processReadingEntry(String userId, LocalDate date, Integer minutesRead) {
        List<Streak> activeStreaks = streakRepository.findActiveByParticipant(userId);
        for (Streak streak : activeStreaks) {
            try {
                recordActivity(streak, userId, date, minutesRead != null ? minutesRead : 0);
                recalculateStreak(streak);
            } catch (Exception e) {
                log.warn("Failed to process streak {} for user {}: {}", streak.getId(), userId, e.getMessage());
            }
        }
    }

    public StreakActivity checkIn(String userId, String streakId, int minutesRead) {
        Streak streak = getStreak(streakId);
        if (!streak.getParticipantIds().contains(userId)) {
            throw new BusinessException("User is not a participant of this streak");
        }
        if (streak.getStatus() != StreakStatus.ACTIVE) {
            throw new BusinessException("Streak is not active");
        }
        StreakActivity activity = recordActivity(streak, userId, LocalDate.now(), minutesRead);
        recalculateStreak(streak);
        return activity;
    }

    private StreakActivity recordActivity(Streak streak, String userId, LocalDate date, int minutesRead) {
        var existing = streakActivityRepository.findByStreakIdAndUserIdAndDate(streak.getId(), userId, date);
        if (existing.isPresent()) {
            StreakActivity activity = existing.get();
            activity.setMinutesRead(activity.getMinutesRead() + minutesRead);
            activity.setCompleted(true);
            return streakActivityRepository.save(activity);
        }
        return streakActivityRepository.save(StreakActivity.builder()
                .streakId(streak.getId())
                .userId(userId)
                .date(date)
                .minutesRead(minutesRead)
                .completed(true)
                .build());
    }

    private void recalculateStreak(Streak streak) {
        LocalDate today = LocalDate.now();

        if (streak.getGoalType() == Streak.GoalType.CONSECUTIVE_DAYS) {
            // All participants must have completed today for the day to count
            boolean allCompletedToday = streak.getParticipantIds().stream()
                    .allMatch(uid -> streakActivityRepository
                            .findByStreakIdAndUserIdAndDate(streak.getId(), uid, today)
                            .map(StreakActivity::isCompleted)
                            .orElse(false));

            if (allCompletedToday) {
                long completedDays = countCompletedGroupDays(streak);
                streak.setCurrentStreak((int) completedDays);
                if (streak.getCurrentStreak() > streak.getBestStreak()) {
                    streak.setBestStreak(streak.getCurrentStreak());
                }
                if (streak.getCurrentStreak() >= streak.getGoalValue()) {
                    streak.setStatus(StreakStatus.COMPLETED);
                }
                streakRepository.save(streak);
            }

        } else if (streak.getGoalType() == Streak.GoalType.TOTAL_MINUTES) {
            int totalMinutes = streakActivityRepository.findByStreakId(streak.getId())
                    .stream().mapToInt(StreakActivity::getMinutesRead).sum();
            streak.setCurrentStreak(totalMinutes);
            if (totalMinutes > streak.getBestStreak()) streak.setBestStreak(totalMinutes);
            if (totalMinutes >= streak.getGoalValue()) streak.setStatus(StreakStatus.COMPLETED);
            streakRepository.save(streak);

        } else if (streak.getGoalType() == Streak.GoalType.TOTAL_PAGES) {
            long total = streakActivityRepository.findByStreakId(streak.getId())
                    .stream().filter(StreakActivity::isCompleted).count();
            streak.setCurrentStreak((int) total);
            if (streak.getCurrentStreak() > streak.getBestStreak()) streak.setBestStreak(streak.getCurrentStreak());
            if (streak.getCurrentStreak() >= streak.getGoalValue()) streak.setStatus(StreakStatus.COMPLETED);
            streakRepository.save(streak);
        }
    }

    private long countCompletedGroupDays(Streak streak) {
        List<StreakActivity> all = streakActivityRepository.findByStreakId(streak.getId());
        int participantCount = streak.getParticipantIds().size();
        return all.stream()
                .filter(StreakActivity::isCompleted)
                .collect(Collectors.groupingBy(
                        StreakActivity::getDate,
                        Collectors.mapping(StreakActivity::getUserId, Collectors.toSet())
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().size() >= participantCount)
                .count();
    }
}
