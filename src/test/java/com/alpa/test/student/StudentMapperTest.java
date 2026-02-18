package com.alpa.test.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {
    private StudentMapper studentMapper;
    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }


    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto studentDto = new StudentDto("Abraham", "Alagbe", "aaa@gmail.com", 1);
        Student student = studentMapper.toStudent(studentDto);

        assertEquals(studentDto.firstname(), student.getFirstname());
        assertEquals(studentDto.firstname(), student.getFirstname());
        assertEquals(studentDto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_student_dto_is_null() {
        var exp = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("the student Dto should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student("Abraham", "Alagbe", "aaa@gmail.com", 1);
        StudentResponseDto responseDto = studentMapper.toStudentResponseDto(student);

        assertEquals(student.getFirstname(), responseDto.firstname());
        assertEquals(student.getLastname(), responseDto.lastname());
        assertEquals(student.getEmail(), responseDto.email());
    }












    // @AfterAll
    // static void afterClass() {
    //     System.out.println("i want you after all");
    // }

    // @BeforeAll
    // static void beforeClass() {
    //     System.out.println("i want you before all");
    // }

    // @BeforeEach
    // void setUp() {
    //     System.out.println("inside the before each method");
    // }

    // @AfterEach
    // void tearDown () {
    //     System.out.println("inside after each method");
    // }

    // @Test
    // public void myFirstTest() {
    //     System.out.println("celebrate my first test");
    // }

    // @Test
    // public void myFirstTest2() {
    //     System.out.println("what is this now first test");
    // }
  
}