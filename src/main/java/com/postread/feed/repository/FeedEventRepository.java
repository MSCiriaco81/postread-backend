package com.postread.feed.repository;

import com.postread.feed.model.FeedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedEventRepository extends MongoRepository<FeedEvent, String> {
    Page<FeedEvent> findByTargetUserIdOrderByCreatedAtDesc(String targetUserId, Pageable pageable);
}
