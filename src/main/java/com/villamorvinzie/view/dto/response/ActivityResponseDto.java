package com.villamorvinzie.view.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.villamorvinzie.view.enums.ActivityType;
import java.time.LocalDate;

@JsonInclude(value = Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ActivityResponseDto(
        String id,
        boolean isDeleted,
        ActivityType type,
        boolean isDone,
        LocalDate dueDate,
        Double amount) {}
