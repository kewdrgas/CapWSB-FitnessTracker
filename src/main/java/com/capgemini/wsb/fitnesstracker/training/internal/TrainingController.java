package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all {@link TrainingDto} objects.
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-user/{userId}")
    public List<TrainingDto> findTrainingByUserId(@PathVariable int userId) {
        List<Training> trainings = trainingService.findByUserId(userId);

        if (trainings.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trainings found for user ID: " + userId);
        }

        return trainings.stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }
    @GetMapping("finished/{afterTime}")
    public List<TrainingDto> findFinishedTrainings(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime) {
        List<Training> trainings = trainingService.findTrainingsFinishedAfter(afterTime);

        if (trainings.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trainings found for date: " + afterTime);
        }

        return trainings.stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
