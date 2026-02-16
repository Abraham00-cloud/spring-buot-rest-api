package com.alpa.test.student;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> findAllStudents() {
        return repository.findAll()
                        .stream()
                        .map(studentMapper::toStudentResponseDto)
                        .toList();
    }

    public StudentResponseDto getStudentById(Integer id) {
        return repository.findById(id)
                    .map(studentMapper::toStudentResponseDto)
                    .orElse(null);
    }

    public List<StudentResponseDto> getStudentByFirstname(String name) {
        return repository.findAllByFirstnameContaining(name)
                            .stream()
                            .map(studentMapper::toStudentResponseDto)
                            .toList();
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
