package com.postread.streak.repository;

import com.postread.streak.model.StreakActivity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StreakActivityRepository extends MongoRepository<StreakActivity, String> {

    Optional<StreakActivity> findByStreakIdAndUserIdAndDate(String streakId, String userId, LocalDate date);

    List<StreakActivity> findByStreakId(String streakId);

    List<StreakActivity> findByStreakIdAndDate(String streakId, LocalDate date);

    List<StreakActivity> findByStreakIdAndUserIdOrderByDateDesc(String streakId, String userId);
}
