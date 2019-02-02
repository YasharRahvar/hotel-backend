package com.hotel.activity.service;

import com.hotel.activity.dto.ActivityDto;
import com.hotel.activity.mapper.ActivityMapper;
import com.hotel.activity.model.Activity;
import com.hotel.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;
    private ActivityMapper activityMapper;

    public ActivityService(ActivityRepository activityRepository, ActivityMapper activityMapper) {

        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    @Transactional
    public Activity createActivity(ActivityDto activityDto, Long hotelId) {
        Activity activity = activityMapper.mapToActivity(activityDto);
        activity.setHotelId(hotelId);
       return activityRepository.save(activity);
    }

    @Transactional
    public void deleteActivity(final Long activityId) {
        this.activityRepository.sofDelete(activityId);
    }
}
