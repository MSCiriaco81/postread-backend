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

@Slf4j
@Service
@RequiredArgsConstructor
public class StreakService {

    private final StreakRepository streakRepository;
    private final StreakActivityRepository streakActivityRepository;

    public Streak createStreak(String creatorId, CreateStreakRequest request) {
        List<String> participants = new ArrayList<>(request.participantIds());
        if (!participants.contains(creatorId)) {
            participants.add(0, creatorId);
        }

        return streakRepository.save(Streak.builder()
                .title(request.title())
                .creatorId(creatorId)
                .participantIds(participants)
                .startDate(LocalDate.now())
                .goalType(request.goalType())
                .goalValue(request.goalValue())
                .build());
    }

    public List<Streak> getMyActiveStreaks(String userId) {
        return streakRepository.findActiveByParticipant(userId);
    }

    public Streak getStreak(String streakId) {
        return streakRepository.findById(streakId)
                .orElseThrow(() -> new ResourceNotFoundException("Streak", streakId));
    }

    /**
     * Called after each reading entry is saved.
     * Updates all active streaks the user participates in.
     */
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
        return recordActivity(streak, userId, LocalDate.now(), minutesRead);
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
        // For CONSECUTIVE_DAYS: check if all participants read today and yesterday
        if (streak.getGoalType() == Streak.GoalType.CONSECUTIVE_DAYS) {
            LocalDate today = LocalDate.now();
            boolean allCompletedToday = streak.getParticipantIds().stream()
                    .allMatch(uid -> streakActivityRepository
                            .findByStreakIdAndUserIdAndDate(streak.getId(), uid, today)
                            .map(StreakActivity::isCompleted)
                            .orElse(false));

            if (allCompletedToday) {
                streak.setCurrentStreak(streak.getCurrentStreak() + 1);
                if (streak.getCurrentStreak() > streak.getBestStreak()) {
                    streak.setBestStreak(streak.getCurrentStreak());
                }
                if (streak.getCurrentStreak() >= streak.getGoalValue()) {
                    streak.setStatus(StreakStatus.COMPLETED);
                }
                streakRepository.save(streak);
            }
        }
    }
}
