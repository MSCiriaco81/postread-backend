package com.postread.streak.repository;

import com.postread.streak.model.Streak;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StreakRepository extends MongoRepository<Streak, String> {

    @Query("{ participantIds: ?0, status: 'ACTIVE' }")
    List<Streak> findActiveByParticipant(String userId);

    List<Streak> findByCreatorId(String creatorId);
}
