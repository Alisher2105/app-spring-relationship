package org.example.appspringrelationship.controller;

import org.apache.catalina.LifecycleState;
import org.example.appspringrelationship.entity.Address;
import org.example.appspringrelationship.entity.University;
import org.example.appspringrelationship.payload.UniversityDto;
import org.example.appspringrelationship.repository.AddressRepository;
import org.example.appspringrelationship.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;


    //READ
    @RequestMapping(value = "/university",method = RequestMethod.GET)
     public List<University> getUniversities(){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    @RequestMapping(value = "/university",method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){
        // Yangi Address ochdim
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        // ochilgan addresslarni DB saqladik va u bizga saqlangan addressni beradi
        Address savedAddress = addressRepository.save(address);

        // Yangi Universitetni name ni qo`shdik va s
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "Universitet saqlandi";
    }
}

