package org.example.appspringrelationship.controller;

import org.example.appspringrelationship.entity.Subject;
import org.example.appspringrelationship.payload.SubjectDto;
import org.example.appspringrelationship.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    // CREATE
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if(existsByName)
            return "Bunday fan mavjud";
        else {
            subjectRepository.save(subject);
            return "Subject qo`shildi";
        }

    }

    // READ
    @GetMapping //@RequestMapping(method = RequsetMethod.GET)
    public List<Subject> getSubject(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    // UPDATE
    @PutMapping
    public String editeSubject(@PathVariable Integer id, @RequestBody SubjectDto subjectDto){
        Optional<Subject> subject2 = subjectRepository.findById(id);
        if(subject2.isPresent()){
            Subject subject = subject2.get();
            subject.setName(subjectDto.getName());
            subjectRepository.save(subject);
            return "Subject nomi o`zgartirildi";
        }
        return "Subject topilmadi";
    }

    //Delete
    @RequestMapping(value = "/subject/{id}",method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable Integer id){
        subjectRepository.deleteById(id);
        return "Student o`chirildi";
    }

}
