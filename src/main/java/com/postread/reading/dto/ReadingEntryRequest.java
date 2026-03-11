package com.postread.reading.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ReadingEntryRequest(
        @NotBlank String bookId,
        @Min(0) Integer minutesRead,
        @Min(0) Integer pagesRead,
        @Min(1) @Max(5) Integer rating,
        @Size(max = 2000) String notes,
        LocalDate date
) {}
