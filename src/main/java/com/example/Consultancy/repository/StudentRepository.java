package com.example.Consultancy.repository;

import com.example.Consultancy.entity.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Repository interface for `student` entity. Provides CRUD operations and custom query methods.*/
@Repository
public interface StudentRepository extends JpaRepository<student, Long> {
    // Custom query methods (if needed) can be defined here
}
