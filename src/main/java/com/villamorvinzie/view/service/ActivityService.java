package com.villamorvinzie.view.service;

import com.villamorvinzie.view.domain.Activity;
import com.villamorvinzie.view.domain.User;
import com.villamorvinzie.view.dto.request.ActivityRequestDto;
import com.villamorvinzie.view.dto.response.ActivityResponseDto;
import com.villamorvinzie.view.exception.ActivityNotFoundException;
import com.villamorvinzie.view.mapper.ActivityMapper;
import com.villamorvinzie.view.repository.ActivityRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityResponseDto createActivity(ActivityRequestDto requestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Activity activity = ActivityMapper.toEntity(requestDto);
        User authUser = (User) authentication.getCredentials();
        activity.setUserId(authUser.getId());
        activityRepository.save(activity);
        return ActivityMapper.toDto(activity);
    }

    public ActivityResponseDto updateActivity(String id, ActivityRequestDto requestDto)
            throws ActivityNotFoundException {
        if (!activityRepository.existsById(id)) {
            throw new ActivityNotFoundException(String.format("Activity with id %s not found.", id));
        }
        Activity activity = ActivityMapper.toEntity(requestDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getCredentials();
        activity.setUserId(authUser.getId());
        activityRepository.save(activity);
        return ActivityMapper.toDto(activity);
    }

    public List<ActivityResponseDto> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = (User) authentication.getCredentials();
        return activityRepository.findAllByUserId(authUser.getId()).stream()
                .map(a -> ActivityMapper.toDto(a))
                .collect(Collectors.toList());
    }

    public void deleteActivity(String id) throws ActivityNotFoundException {
        if (!activityRepository.existsById(id)) {
            throw new ActivityNotFoundException(String.format("Activity with id %s not found.", id));
        }
        activityRepository.deleteById(id);
    }
}
