package com.tau.project.repositories;

import com.tau.project.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface User_Repository extends JpaRepository<Users, Long> {
}



