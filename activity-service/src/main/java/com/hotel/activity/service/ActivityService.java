package com.hotel.activity.service;

import com.hotel.activity.model.Activity;
import com.hotel.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityService {
    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {

        this.activityRepository = activityRepository;
    }

    @Transactional
    public Activity createActivity(Activity activity) {
       return activityRepository.save(activity);
    }

    @Transactional
    public void deleteActivity(final Long activityId) {
        this.activityRepository.sofDelete(activityId);
    }
}
