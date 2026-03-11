package com.postread.reading.repository;

import com.postread.reading.model.ReadingEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReadingEntryRepository extends MongoRepository<ReadingEntry, String> {

    Page<ReadingEntry> findByUserIdOrderByDateDesc(String userId, Pageable pageable);

    List<ReadingEntry> findByUserIdAndDateBetween(String userId, LocalDate from, LocalDate to);

    List<ReadingEntry> findByUserIdAndBookId(String userId, String bookId);

    boolean existsByUserIdAndDate(String userId, LocalDate date);

    @Aggregation(pipeline = {
        "{ $match: { userId: ?0 } }",
        "{ $group: { _id: null, totalMinutes: { $sum: '$minutesRead' }, totalPages: { $sum: '$pagesRead' } } }"
    })
    ReadingStats sumByUserId(String userId);

    interface ReadingStats {
        Integer getTotalMinutes();
        Integer getTotalPages();
    }
}
