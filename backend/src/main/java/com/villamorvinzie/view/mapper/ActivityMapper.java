package com.villamorvinzie.view.mapper;

import com.villamorvinzie.view.domain.Activity;
import com.villamorvinzie.view.dto.request.ActivityRequestDto;
import com.villamorvinzie.view.dto.response.ActivityResponseDto;
import java.time.LocalDateTime;

public class ActivityMapper {
    public static Activity toEntity(ActivityRequestDto dto) {
        Activity activity =
                new Activity(
                        null,
                        null,
                        dto.isDeleted(),
                        dto.type(),
                        dto.isDone(),
                        dto.dueDate(),
                        LocalDateTime.now(),
                        dto.amount(),
                        dto.description());
        return activity;
    }

    public static ActivityResponseDto toDto(Activity e) {
        return new ActivityResponseDto(
                e.getId(),
                e.isDeleted(),
                e.getType(),
                e.isDone(),
                e.getDueDate(),
                e.getCreated(),
                e.getDescription(),
                e.getAmount());
    }
}
