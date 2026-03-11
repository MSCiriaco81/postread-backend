package com.postread.feed.service;

import com.postread.feed.model.FeedEvent;
import com.postread.feed.model.FeedEvent.EventType;
import com.postread.feed.repository.FeedEventRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FeedService")
class FeedServiceTest {

    @Mock FeedEventRepository feedEventRepository;
    @InjectMocks FeedService feedService;

    @Test
    @DisplayName("createEvent: deve salvar e retornar o evento")
    void createEvent_savesEvent() {
        var event = FeedEvent.builder()
                .actorUserId("user-1")
                .targetUserId("user-2")
                .eventType(EventType.READING_LOGGED)
                .payload(Map.of("bookId", "book-1"))
                .build();

        when(feedEventRepository.save(any(FeedEvent.class))).thenReturn(event);

        FeedEvent saved = feedService.createEvent(event);

        assertThat(saved.getEventType()).isEqualTo(EventType.READING_LOGGED);
        assertThat(saved.getActorUserId()).isEqualTo("user-1");
        verify(feedEventRepository).save(event);
    }

    @Test
    @DisplayName("getFeed: deve retornar página de eventos do usuário")
    void getFeed_returnsPaginatedEvents() {
        var pageable = PageRequest.of(0, 20);
        var event = FeedEvent.builder()
                .actorUserId("user-2")
                .targetUserId("user-1")
                .eventType(EventType.STREAK_MAINTAINED)
                .build();
        Page<FeedEvent> page = new PageImpl<>(List.of(event));

        when(feedEventRepository.findByTargetUserIdOrderByCreatedAtDesc("user-1", pageable))
                .thenReturn(page);

        Page<FeedEvent> result = feedService.getFeed("user-1", pageable);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getTargetUserId()).isEqualTo("user-1");
    }

    @Test
    @DisplayName("getFeed: deve retornar página vazia quando não há eventos")
    void getFeed_noEvents_returnsEmptyPage() {
        var pageable = PageRequest.of(0, 20);
        when(feedEventRepository.findByTargetUserIdOrderByCreatedAtDesc("user-1", pageable))
                .thenReturn(Page.empty());

        Page<FeedEvent> result = feedService.getFeed("user-1", pageable);

        assertThat(result.getContent()).isEmpty();
    }
}
