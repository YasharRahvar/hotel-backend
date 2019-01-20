package com.hotel.activity.controller;

import com.hotel.activity.dto.ActivityDto;
import com.hotel.activity.mapper.ActivityMapper;
import com.hotel.activity.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ActivityController {

    private ActivityService activityService;
    private ActivityMapper activityMapper;

    public ActivityController(ActivityService activityService, ActivityMapper activityMapper) {

        this.activityService = activityService;
        this.activityMapper = activityMapper;
    }

    @PostMapping("/activity")
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public ActivityDto createActivity(@RequestBody ActivityDto activityDto) {
        log.info("this is a activityDto " + activityDto);
        return activityMapper.mapToActivityDto(activityService.createActivity(activityMapper.mapToActivity(activityDto)));
    }

    @DeleteMapping("/activity/{activityId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
    }
}
