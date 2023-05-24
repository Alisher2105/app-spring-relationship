package org.example.appspringrelationship.repository;

import org.example.appspringrelationship.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findAllByFaculty_University_Id(Integer faculty_university_id);
}
