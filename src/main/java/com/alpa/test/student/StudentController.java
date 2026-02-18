package com.alpa.test.student;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alpa.test.Order;
import com.alpa.test.OrderRecord;

import jakarta.validation.Valid;

@RestController
public class StudentController {
    private final StudentService studentService;


    
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(
        @Valid @RequestBody StudentDto dto
    ) {
        return this.studentService.saveStudent(dto);
    }

   


    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudents() {
        return this.studentService.findAllStudents();
    }

    @GetMapping("/students/{student_id}")
    public StudentResponseDto getStudentById(
        @PathVariable("student_id") Integer id
    ) {
        return this.studentService.getStudentById(id);
    }

    @GetMapping("/students/search/{student_name}")
    public List<StudentResponseDto> getStudentByFirstname(
        @PathVariable("student_name") String name
    ) {
        return this.studentService.getStudentByFirstname(name);
    }

    @DeleteMapping("/students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
        @PathVariable("student_id") Integer id
    ) {
        this.studentService.delete(id);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <?> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exp
    ) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                        .forEach(error -> {
                            var filename = ((FieldError) error).getField();
                            var errorMessage = error.getDefaultMessage();
                            errors.put(filename, errorMessage);
                        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }















    // @GetMapping("/hello")
    // public String sayHello() {
    //     return "hello from springboot";
    // }



    @GetMapping("/hello-2")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello2() {
        return "hello from springboot2";
    }

    @PostMapping("/post")
    public String post(@RequestBody String message) {
        return "hello message is " + message;
    }

    @PostMapping("/post-order")
    public String post(@RequestBody Order order) {
        return "hello, your order has been accepted and your message is: " + order.toString();
    }

    @PostMapping("/post-order-record")
    public String post(@RequestBody OrderRecord order) {
        return "hello, your order has been accepted and your message is: " + order.toString();
    }

    @GetMapping("/hello/{userName}")
    public String pathVar(@PathVariable String userName) {
        return "hello from springboot, username is " + userName;
    }

        @GetMapping("/hello")
    public String paramVar(
        @RequestParam("userName") String userName,
        @RequestParam("age") int age) {
        return "hello from springboot, username is " + userName + " age = " + age ;
    }
}
