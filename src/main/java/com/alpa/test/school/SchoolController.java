package com.alpa.test.school;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {
    private SchoolService schoolService;



    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }



    @PostMapping("/schools")
    public SchoolDto create(
        @RequestBody SchoolDto dto
    ){
        return  schoolService.saveSchool(dto);
    }


    @GetMapping("/schools")
    public List<SchoolDto> findAll(){
        return schoolService.findAll();
    }
}

