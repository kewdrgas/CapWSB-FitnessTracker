package com.capgemini.wsb.fitnesstracker.training.internal;


import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

public record TrainingDto(
        Long id,
        UserDto user,  // user is of type UserDto
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime,
        ActivityType activityType,
        double distance,
        double averageSpeed
) {}

