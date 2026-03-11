package com.postread.reading.service;

import com.postread.feed.model.FeedEvent;
import com.postread.feed.service.FeedService;
import com.postread.reading.dto.ReadingEntryRequest;
import com.postread.reading.model.ReadingEntry;
import com.postread.reading.repository.ReadingEntryRepository;
import com.postread.shared.exception.ResourceNotFoundException;
import com.postread.social.service.SocialService;
import com.postread.streak.service.StreakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadingService {

    private final ReadingEntryRepository readingEntryRepository;
    private final StreakService streakService;
    private final FeedService feedService;
    private final SocialService socialService;

    public ReadingEntry logReading(String userId, ReadingEntryRequest request) {
        LocalDate date = request.date() != null ? request.date() : LocalDate.now();

        ReadingEntry entry = ReadingEntry.builder()
                .userId(userId)
                .bookId(request.bookId())
                .minutesRead(request.minutesRead())
                .pagesRead(request.pagesRead())
                .rating(request.rating())
                .notes(request.notes())
                .date(date)
                .build();

        entry = readingEntryRepository.save(entry);

        // Update streaks and notify friends
        streakService.processReadingEntry(userId, date, request.minutesRead());
        broadcastToFriends(userId, entry);

        return entry;
    }

    public Page<ReadingEntry> getUserReadings(String userId, Pageable pageable) {
        return readingEntryRepository.findByUserIdOrderByDateDesc(userId, pageable);
    }

    public List<ReadingEntry> getUserReadingsInRange(String userId, LocalDate from, LocalDate to) {
        return readingEntryRepository.findByUserIdAndDateBetween(userId, from, to);
    }

    public ReadingEntry getEntry(String entryId) {
        return readingEntryRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("ReadingEntry", entryId));
    }

    public void deleteEntry(String userId, String entryId) {
        ReadingEntry entry = getEntry(entryId);
        if (!entry.getUserId().equals(userId)) {
            throw new org.springframework.security.access.AccessDeniedException("Not your entry");
        }
        readingEntryRepository.delete(entry);
    }

    private void broadcastToFriends(String userId, ReadingEntry entry) {
        try {
            List<String> friendIds = socialService.getFriendIds(userId);
            for (String friendId : friendIds) {
                feedService.createEvent(FeedEvent.builder()
                        .actorUserId(userId)
                        .targetUserId(friendId)
                        .eventType(FeedEvent.EventType.READING_LOGGED)
                        .payload(Map.of(
                                "entryId", entry.getId(),
                                "bookId", entry.getBookId(),
                                "minutes", String.valueOf(entry.getMinutesRead() != null ? entry.getMinutesRead() : 0)
                        ))
                        .build());
            }
        } catch (Exception e) {
            log.warn("Failed to broadcast reading entry to feed: {}", e.getMessage());
        }
    }
}
