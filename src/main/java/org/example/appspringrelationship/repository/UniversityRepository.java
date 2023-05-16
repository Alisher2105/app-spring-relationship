package org.example.appspringrelationship.repository;

import org.example.appspringrelationship.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UniversityRepository extends JpaRepository<University,Integer> {
}
