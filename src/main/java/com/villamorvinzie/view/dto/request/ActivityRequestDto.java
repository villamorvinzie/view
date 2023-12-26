package com.villamorvinzie.view.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.villamorvinzie.view.enums.ActivityType;
import java.time.LocalDate;

@JsonInclude(value = Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ActivityRequestDto(
        String id,
        ActivityType type,
        Boolean isDone,
        LocalDate dueDate,
        Double amount,
        Boolean isDeleted) {}
