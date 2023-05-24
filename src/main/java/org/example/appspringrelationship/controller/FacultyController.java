package org.example.appspringrelationship.controller;

import org.apache.catalina.LifecycleState;
import org.example.appspringrelationship.entity.Faculty;
import org.example.appspringrelationship.entity.University;
import org.example.appspringrelationship.payload.FacultyDto;
import org.example.appspringrelationship.repository.FacultyRepository;
import org.example.appspringrelationship.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")

public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    // VAzirlik hamma fakultetlarni ko`risi mumkin
    @GetMapping
    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }

    @PostMapping // Fakultet qo`shish
    public String addFaculty(@RequestBody FacultyDto facultyDto){
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if(exists)
            return "Bu universitetda bunday fakultet bor";
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isEmpty())
            return "Universitet topilmadi";
        faculty.setUniversity(optionalUniversity.get());

        facultyRepository.save(faculty);
        return "Facultet saqlandi";

    }

    //Universitet uchun fakultet ro`yhatini olish universitet faqat `o`zidagi fakultetlarni
    // ko`ra oladi
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }
}
