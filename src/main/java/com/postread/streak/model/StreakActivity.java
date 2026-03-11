package com.postread.streak.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "streak_activities")
@CompoundIndex(def = "{'streakId': 1, 'userId': 1, 'date': 1}", unique = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreakActivity {

    @Id
    private String id;

    private String streakId;

    private String userId;

    private LocalDate date;

    @Builder.Default
    private int minutesRead = 0;

    @Builder.Default
    private boolean completed = false;
}
