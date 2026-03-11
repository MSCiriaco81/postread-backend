package com.postread.reading.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Document(collection = "reading_entries")
@CompoundIndex(def = "{'userId': 1, 'date': -1}")
@CompoundIndex(def = "{'userId': 1, 'bookId': 1}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadingEntry {

    @Id
    private String id;

    private String userId;

    private String bookId;

    private Integer minutesRead;

    private Integer pagesRead;

    private Integer rating;       // 1-5

    private String notes;

    private LocalDate date;

    @CreatedDate
    private Instant createdAt;
}
