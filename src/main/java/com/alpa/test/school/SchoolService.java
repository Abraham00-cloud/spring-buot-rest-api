package com.alpa.test.school;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;


    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public SchoolDto saveSchool(SchoolDto dto){
        var school = schoolMapper.toSchool(dto);
        var savedSchool = schoolRepository.save(school);
        return new SchoolDto(savedSchool.getName());
    }

    public List<SchoolDto> findAll(){
        return schoolRepository.findAll()
                    .stream()
                    .map(schoolMapper::toSchoolDto)
                    .toList();
    }

}
