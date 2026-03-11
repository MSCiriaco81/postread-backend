package com.postread.feed.service;

import com.postread.feed.model.FeedEvent;
import com.postread.feed.repository.FeedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedEventRepository feedEventRepository;

    public FeedEvent createEvent(FeedEvent event) {
        return feedEventRepository.save(event);
    }

    public Page<FeedEvent> getFeed(String userId, Pageable pageable) {
        return feedEventRepository.findByTargetUserIdOrderByCreatedAtDesc(userId, pageable);
    }
}
