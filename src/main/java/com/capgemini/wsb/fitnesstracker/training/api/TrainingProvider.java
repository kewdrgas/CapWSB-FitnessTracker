package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions.
     *
     * @return a list of all {@link Training} entities.
     */
    List<Training> findAllTrainings();

    /**
     * Retreives trainings with particular user id.
     * @param userId
     * @return a list of {@link Training} that have particular user
     */
    List<Training> findByUserId(int userId);

    /**
     * Retrieves all finished trainings that occurred after a specific time.
     *
     * @param afterTime the date after which finished trainings should be fetched
     * @return a list of {@link Training} entities that are finished after the specified date
     */
    List<Training> findTrainingsFinishedAfter(Date afterTime);
}
