package com.postread.streak.service;

import com.postread.shared.exception.BusinessException;
import com.postread.shared.exception.ResourceNotFoundException;
import com.postread.streak.dto.CreateStreakRequest;
import com.postread.streak.model.Streak;
import com.postread.streak.model.Streak.GoalType;
import com.postread.streak.model.Streak.StreakStatus;
import com.postread.streak.model.StreakActivity;
import com.postread.streak.repository.StreakActivityRepository;
import com.postread.streak.repository.StreakRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("StreakService")
class StreakServiceTest {

    @Mock StreakRepository streakRepository;
    @Mock StreakActivityRepository streakActivityRepository;

    @InjectMocks StreakService streakService;

    private Streak mockStreak;

    @BeforeEach
    void setUp() {
        mockStreak = Streak.builder()
                .id("streak-1")
                .title("7 dias seguidos")
                .creatorId("user-1")
                .participantIds(new ArrayList<>(List.of("user-1", "user-2")))
                .startDate(LocalDate.now())
                .goalType(GoalType.CONSECUTIVE_DAYS)
                .goalValue(7)
                .currentStreak(0)
                .bestStreak(0)
                .status(StreakStatus.ACTIVE)
                .build();
    }

    // ── createStreak ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("createStreak: deve incluir creator na lista de participantes")
    void createStreak_includesCreatorInParticipants() {
        var request = new CreateStreakRequest("7 dias", List.of("user-2"), GoalType.CONSECUTIVE_DAYS, 7);
        when(streakRepository.save(any(Streak.class))).thenReturn(mockStreak);

        Streak result = streakService.createStreak("user-1", request);

        verify(streakRepository).save(argThat(s -> s.getParticipantIds().contains("user-1")));
    }

    @Test
    @DisplayName("createStreak: não deve duplicar creator quando já está na lista")
    void createStreak_noDuplicateCreator() {
        var request = new CreateStreakRequest("7 dias", List.of("user-1", "user-2"), GoalType.CONSECUTIVE_DAYS, 7);
        when(streakRepository.save(any(Streak.class))).thenReturn(mockStreak);

        streakService.createStreak("user-1", request);

        verify(streakRepository).save(argThat(s ->
                s.getParticipantIds().stream().filter(id -> id.equals("user-1")).count() == 1
        ));
    }

    // ── checkIn ───────────────────────────────────────────────────────────────

    @Test
    @DisplayName("checkIn: deve registrar atividade para participante válido")
    void checkIn_validParticipant_recordsActivity() {
        when(streakRepository.findById("streak-1")).thenReturn(Optional.of(mockStreak));
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(any(), any(), any()))
                .thenReturn(Optional.empty());
        when(streakActivityRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        // verifica se o usuário atual já completou a atividade hoje (usado pelo recalculate interno)
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(eq("streak-1"), eq("user-1"), any()))
                .thenReturn(Optional.of(StreakActivity.builder().completed(true).build()));

        StreakActivity activity = streakService.checkIn("user-1", "streak-1", 30);

        assertThat(activity).isNotNull();
        assertThat(activity.getMinutesRead()).isEqualTo(30);
        assertThat(activity.isCompleted()).isTrue();
    }

    @Test
    @DisplayName("checkIn: deve lançar BusinessException quando usuário não é participante")
    void checkIn_notParticipant_throwsBusiness() {
        when(streakRepository.findById("streak-1")).thenReturn(Optional.of(mockStreak));

        assertThatThrownBy(() -> streakService.checkIn("user-estranho", "streak-1", 30))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("not a participant");
    }

    // ── processReadingEntry ───────────────────────────────────────────────────

    @Test
    @DisplayName("processReadingEntry: deve processar todos os streaks ativos do usuário")
    void processReadingEntry_processesAllActiveStreaks() {
        when(streakRepository.findActiveByParticipant("user-1")).thenReturn(List.of(mockStreak));
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(any(), any(), any()))
                .thenReturn(Optional.empty());
        when(streakActivityRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(eq("streak-1"), anyString(), any()))
                .thenReturn(Optional.of(StreakActivity.builder().completed(false).build()));

        streakService.processReadingEntry("user-1", LocalDate.now(), 45);

        verify(streakActivityRepository, atLeastOnce()).save(any(StreakActivity.class));
    }

    @Test
    @DisplayName("processReadingEntry: deve incrementar currentStreak quando todos completam no dia")
    void processReadingEntry_allComplete_incrementsStreak() {
        when(streakRepository.findActiveByParticipant("user-1")).thenReturn(List.of(mockStreak));
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(any(), any(), any()))
                .thenReturn(Optional.empty());
        when(streakActivityRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // Ambos completaram hoje
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(eq("streak-1"), eq("user-1"), any()))
                .thenReturn(Optional.of(StreakActivity.builder().completed(true).build()));
        when(streakActivityRepository.findByStreakIdAndUserIdAndDate(eq("streak-1"), eq("user-2"), any()))
                .thenReturn(Optional.of(StreakActivity.builder().completed(true).build()));
        when(streakRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        streakService.processReadingEntry("user-1", LocalDate.now(), 30);

        verify(streakRepository).save(argThat(s -> s.getCurrentStreak() == 1));
    }

    // ── getStreak ─────────────────────────────────────────────────────────────

    @Test
    @DisplayName("getStreak: deve lançar ResourceNotFoundException para ID inválido")
    void getStreak_notFound_throws() {
        when(streakRepository.findById("nao-existe")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> streakService.getStreak("nao-existe"))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
