package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    /**
     * Retrieves a training session by its unique ID.
     *
     * @param trainingId the ID of the training session.
     * @return an Optional containing the {@link Training} if found, otherwise an empty Optional.
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all {@link Training} entities.
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }
    @Override
    public List<Training> findByUserId(int userId) {
        List<Training> trainings = trainingRepository.findByUser_Id(userId);
        if (trainings.isEmpty()) {
            log.warn("No trainings found for user ID: {}", userId);
        }
        return trainings;
    }

    @Override
    public List<Training> findTrainingsFinishedAfter(Date afterTime){
        return trainingRepository.findByEndTimeAfter(afterTime);
    }
}
