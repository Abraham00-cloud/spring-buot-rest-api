package com.alpa.test.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {

        //service declaration
    @InjectMocks
    private StudentService studentService;

        //dependencies
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_register_student_succesfully() {
        //given
        StudentDto studentDto = new StudentDto("Abraham", "Alagbe", "aaa@gmail.com", 1);

        Student student = new Student("Abraham", "Alagbe", "aaa@gmail.com", 15);

        Student savedStudent = new Student("Abraham", "Alagbe", "aaa@gmail.com", 15);
        savedStudent.setId(1);

        //mock calls
        when(studentMapper.toStudent(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("Abraham", "Alagbe", "aaa@gmail.com"));

        //when
        StudentResponseDto  responseDto = studentService.saveStudent(studentDto);


        //then
        assertEquals(studentDto.firstname(), responseDto.firstname());
        assertEquals(studentDto.lastname(), responseDto.lastname());
        assertEquals(studentDto.email(), responseDto.email());

        //to make sure it is running just once to avoid error when it is a unique field
        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);


    }

    @Test
    public void should_return_all_student() {
        //given
        List<Student> student = new ArrayList<>();
        student.add(new Student("Abraham", "Alagbe", "aaa@gmail.com", 15));

        //mock calls
        when(studentRepository.findAll()).thenReturn(student);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn( new StudentResponseDto("Abraham", "Alagbe", "aaa@gmail.com"));

        //when
        List<StudentResponseDto> responseDtos = studentService.findAllStudents();

        //then
        assertEquals(student.size(), responseDtos.size());


        //verify
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id_correctly() {
        //given
        Student student = new Student("Abraham", "Alagbe", "aaa@gmail.com", 15);
        student.setId(1);  //this could easily be creating a new Integer studentId

        //mock calls
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto("Abraham", "Alagbe", "aaa@gmail.com"));

        //when
        StudentResponseDto responseDto = studentService.getStudentById(student.getId());

        //then
        assertEquals(responseDto.firstname(), student.getFirstname());
        assertEquals(responseDto.lastname(), student.getLastname());
        assertEquals(responseDto.email(), student.getEmail());

        verify(studentRepository, times(1)).findById(student.getId());

    }

    @Test
    public void should_return_student_by_firstname_correctly() {
        //given
        List<Student> students = new ArrayList<>();
        students.add(new Student("Abraham", "Alagbe", "aaa@gmail.com", 15));
        String firstname = "Abraham";

        //mock calls
        when(studentRepository.findAllByFirstnameContaining(firstname)).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class))).thenReturn(new StudentResponseDto("Abraham", "Alagbe", "aaa@gmail.com"));

        //when
        List<StudentResponseDto> responseDtos = studentService.getStudentByFirstname(firstname);

        //then
        assertEquals(students.size(), responseDtos.size());

        verify(studentRepository, times(1)).findAllByFirstnameContaining(firstname);

    }

    @Test
    public void should_delete_student_by_id() {
        //given
        Student student = new Student("Abraham", "Alagbe", "aaa@gmail.com", 15);
        Integer studentId = 1;

        //when
        studentService.delete(studentId);

        //then
        verify(studentRepository, times(1)).deleteById(studentId);


    }
}
