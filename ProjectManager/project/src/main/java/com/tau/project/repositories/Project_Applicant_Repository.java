package com.tau.project.repositories;

import com.tau.project.models.Project_Applicant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
@Transactional(readOnly = true)
public interface Project_Applicant_Repository extends JpaRepository<Project_Applicant, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Project_Applicant a " +
           "SET a.isAccepted = TRUE " + 
           "WHERE a.user_id = ?1 and a.project_id = ?2")
    public void accept_project_applier(Long user_id, Long project_id);

}
