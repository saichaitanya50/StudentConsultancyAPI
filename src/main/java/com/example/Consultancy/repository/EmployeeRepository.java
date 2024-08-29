package com.example.Consultancy.repository;

import com.example.Consultancy.entity.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* Repository interface for `employee` entity. Provides CRUD operations and custom query methods. */
@Repository
public interface EmployeeRepository extends JpaRepository<employee, Long> {
    // Custom query methods (if needed) can be defined here
}
