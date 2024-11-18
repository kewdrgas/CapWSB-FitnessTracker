package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.UserDto;
import com.capgemini.wsb.fitnesstracker.user.internal.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    private final UserMapper userMapper;

    /**

     * @param userMapper the UserMapper dependency
     */
    public TrainingMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Converts a {@link Training} entity to a {@link TrainingDto}.
     *
     * @param training the Training entity
     * @return the corresponding TrainingDto
     */
    public TrainingDto toDto(Training training) {
        UserDto userDto = training.getUser() != null ? userMapper.toDto(training.getUser()) : null;

        return new TrainingDto(
                training.getId(),
                userDto,
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     *
     * @param trainingDto the TrainingDto object
     * @return the corresponding Training entity
     */
    public Training toEntity(TrainingDto trainingDto) {
        ActivityType activityType = ActivityType.valueOf(trainingDto.activityType().toString()); // Adjusted for proper enum mapping

        return new Training(
                null,
                trainingDto.startTime(),
                trainingDto.endTime(),
                activityType,
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }
}
