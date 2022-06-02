package com.tau.project.repositories;

import java.sql.Date;
import java.util.ArrayList;

import com.tau.project.models.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
@Transactional(readOnly = true)
public interface Project_Repsitory extends JpaRepository<Project, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.project_title = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateTitle(Long project_id, String title);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.project_description = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateDescription(Long project_id, String project_description);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.start_date = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateStartDate(Long project_id, Date start_date);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.end_date = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateEndDate(Long project_id, Date end_date);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.tasks = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateTasks(Long project_id, ArrayList<String> tasks);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.price = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updatePrice(Long project_id, Double price);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.project_programming_languages = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateLanguages(Long project_id, ArrayList<String> project_programming_languages);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.project_preference_tags = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateTags(Long project_id, ArrayList<String> project_preference_tags);

    @Transactional
    @Modifying
    @Query("UPDATE Project a " +
           "SET a.status = ?2 " + 
           "WHERE a.project_id = ?1")
    public void updateStatus(Long project_id, String status);

}