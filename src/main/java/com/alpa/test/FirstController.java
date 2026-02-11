package com.alpa.test;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {


    private final StudentRepository repository;

    public FirstController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/students")
    public Student post(
        @RequestBody Student student
    ) {
        return repository.save(student);
    }

    @GetMapping("/students")
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    @GetMapping("/students/{student_id}")
    public Student getStudentById(
        @PathVariable("student_id") Integer id
    ) {
        return repository.findById(id)
                    .orElse(new Student());
    }

    @GetMapping("/students/search/{student_name}")
    public List<Student> getStudentByFirstname(
        @PathVariable("student_name") String name
    ) {
        return repository.findAllByFirstnameContaining(name);
    }

    @DeleteMapping("/students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(
        @PathVariable("student_id") Integer id
    ) {
        repository.deleteById(id);
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
