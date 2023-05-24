package org.example.appspringrelationship.controller;

import org.example.appspringrelationship.entity.Faculty;
import org.example.appspringrelationship.entity.Group;
import org.example.appspringrelationship.payload.GroupDto;
import org.example.appspringrelationship.repository.FacultyRepository;
import org.example.appspringrelationship.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    // Vazirlik uchun
    // READ
    @GetMapping
    public List<org.example.appspringrelationship.entity.Group> getGroups(){
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    // Universitet masul xodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<org.example.appspringrelationship.entity.Group> getGroupsByUniversityId(@PathVariable Integer universityId){
        List<org.example.appspringrelationship.entity.Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
        return allByFaculty_university_id;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if(optionalFaculty.isEmpty())
            return "Bunday fakultet yo`q";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Guruh qo`shildi";

    }
}
