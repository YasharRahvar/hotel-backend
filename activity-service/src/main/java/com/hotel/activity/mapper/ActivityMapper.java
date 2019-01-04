package com.hotel.activity.mapper;

import com.hotel.activity.dto.ActivityDto;
import com.hotel.activity.model.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity mapToActivity(ActivityDto activityDto);
    ActivityDto mapToActivityDto(Activity activity);
}
