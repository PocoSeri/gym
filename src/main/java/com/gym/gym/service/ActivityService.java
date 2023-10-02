package com.gym.gym.service;

import com.gym.gym.base.repository.FilterableRepository;
import com.gym.gym.base.service.BaseService;
import com.gym.gym.base.service.IBaseService;
import com.gym.gym.entity.Activity;
import com.gym.gym.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityService extends BaseService<Activity, String> implements IBaseService<Activity, String> {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository, FilterableRepository filterableRepository) {
        super(activityRepository, filterableRepository);
        this.activityRepository = activityRepository;
    }
}

