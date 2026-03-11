package com.postread.reading.service;

import com.postread.feed.service.FeedService;
import com.postread.reading.dto.ReadingEntryRequest;
import com.postread.reading.model.ReadingEntry;
import com.postread.reading.repository.ReadingEntryRepository;
import com.postread.shared.exception.ResourceNotFoundException;
import com.postread.social.service.SocialService;
import com.postread.streak.service.StreakService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReadingService")
class ReadingServiceTest {

    @Mock ReadingEntryRepository readingEntryRepository;
    @Mock StreakService streakService;
    @Mock FeedService feedService;
    @Mock SocialService socialService;

    @InjectMocks ReadingService readingService;

    private ReadingEntry mockEntry;

    @BeforeEach
    void setUp() {
        mockEntry = ReadingEntry.builder()
                .id("entry-1")
                .userId("user-1")
                .bookId("book-1")
                .minutesRead(45)
                .pagesRead(30)
                .date(LocalDate.now())
                .build();
    }

    @Test
    @DisplayName("logReading: deve salvar entrada e disparar streak e feed")
    void logReading_savesEntryAndTriggersSideEffects() {
        var request = new ReadingEntryRequest("book-1", 45, 30, null, null, null);
        when(readingEntryRepository.save(any(ReadingEntry.class))).thenReturn(mockEntry);
        when(socialService.getFriendIds("user-1")).thenReturn(List.of("friend-1"));

        ReadingEntry result = readingService.logReading("user-1", request);

        assertThat(result.getId()).isEqualTo("entry-1");
        assertThat(result.getMinutesRead()).isEqualTo(45);
        verify(streakService).processReadingEntry(eq("user-1"), any(LocalDate.class), eq(45));
        verify(feedService).createEvent(any());
    }

    @Test
    @DisplayName("logReading: deve usar data de hoje quando date não é fornecida")
    void logReading_nullDate_usesToday() {
        var request = new ReadingEntryRequest("book-1", 30, 20, null, null, null);
        when(readingEntryRepository.save(any(ReadingEntry.class))).thenReturn(mockEntry);
        when(socialService.getFriendIds(any())).thenReturn(List.of());

        readingService.logReading("user-1", request);

        verify(readingEntryRepository).save(argThat(e -> e.getDate().equals(LocalDate.now())));
    }

    @Test
    @DisplayName("logReading: deve usar data fornecida quando presente")
    void logReading_withDate_usesProvidedDate() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        var request = new ReadingEntryRequest("book-1", 30, 20, null, null, yesterday);
        when(readingEntryRepository.save(any(ReadingEntry.class))).thenReturn(mockEntry);
        when(socialService.getFriendIds(any())).thenReturn(List.of());

        readingService.logReading("user-1", request);

        verify(readingEntryRepository).save(argThat(e -> e.getDate().equals(yesterday)));
    }

    @Test
    @DisplayName("getUserReadings: deve retornar página de entradas do usuário")
    void getUserReadings_returnsPage() {
        var pageable = PageRequest.of(0, 20);
        Page<ReadingEntry> page = new PageImpl<>(List.of(mockEntry));
        when(readingEntryRepository.findByUserIdOrderByDateDesc("user-1", pageable)).thenReturn(page);

        Page<ReadingEntry> result = readingService.getUserReadings("user-1", pageable);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getBookId()).isEqualTo("book-1");
    }

    @Test
    @DisplayName("deleteEntry: deve deletar quando usuário é o dono")
    void deleteEntry_owner_deletes() {
        when(readingEntryRepository.findById("entry-1")).thenReturn(Optional.of(mockEntry));

        readingService.deleteEntry("user-1", "entry-1");

        verify(readingEntryRepository).delete(mockEntry);
    }

    @Test
    @DisplayName("deleteEntry: deve lançar AccessDeniedException quando usuário não é o dono")
    void deleteEntry_notOwner_throwsAccessDenied() {
        when(readingEntryRepository.findById("entry-1")).thenReturn(Optional.of(mockEntry));

        assertThatThrownBy(() -> readingService.deleteEntry("outro-user", "entry-1"))
                .isInstanceOf(AccessDeniedException.class);

        verify(readingEntryRepository, never()).delete(any());
    }

    @Test
    @DisplayName("getEntry: deve lançar ResourceNotFoundException quando não existe")
    void getEntry_notFound_throws() {
        when(readingEntryRepository.findById("nao-existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> readingService.getEntry("nao-existe"))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
