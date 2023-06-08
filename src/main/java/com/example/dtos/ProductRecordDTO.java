package com.example.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public record ProductRecordDTO(@NotBlank String name, @NotNull BigDecimal value) {
}
