package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Finds all training sessions for a specific user by user ID.
     *
     * @param userId the ID of the user.
     * @return a list of training sessions associated with the given user.
     */
    @Query("SELECT t FROM Training t WHERE t.user.id = :userId")
    List<Training> findByUser_Id(int userId);
}
