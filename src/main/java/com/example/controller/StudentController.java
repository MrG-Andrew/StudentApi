package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;
    @GetMapping("getAll")
    public List<StudentResponse> getAllStudents() {
        List<Student> studentList =  studentService.getAllStudents();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("getAllWithSorting")
    public List<StudentResponse> getAllWithSorting() {
        List<Student> studentList =  studentService.getAllWithSorting();
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @PostMapping("create")
    public StudentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest){
        Student student = studentService.createStudent(createStudentRequest);

        return new StudentResponse(student);
    }

    @PutMapping("update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
        Student student = studentService.updateStudent(updateStudentRequest);

        return new StudentResponse(student);
    }

//    @DeleteMapping("delete")
//    public String deleteStudent (@RequestParam Long id){
//        return studentService.deleteStudent(id);
//    }

    @DeleteMapping("delete/{id}")
    public String deleteStudent (@PathVariable Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName){
        List <Student> studentList = studentService.getByFirstName(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;

    }

    @GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
    public List <StudentResponse> getByFirstNameAndLastName(@PathVariable String firstName,@PathVariable String lastName){
        List <Student> studentList = studentService.getByFirstNameAndLastName(firstName, lastName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
    public List <StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName,@PathVariable String lastName){
        List <Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("getByFirstNameIn")
    public List <StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest){
        List <Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("getAllWithPagination")
    public List<StudentResponse> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize){
        List <Student> studentList = studentService.getAllWithPagination(pageNo, pageSize);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("like/{firstName}")
    public List<StudentResponse> like(@PathVariable String firstName){
        List <Student> studentList = studentService.like(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @GetMapping("startsWith/{firstName}")
    public List<StudentResponse> startsWith(@PathVariable String firstName){
        List <Student> studentList = studentService.startsWith(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    @PutMapping("updateFirstName/{id}/{firstName}")
    public String updateFirstNameWithJPQL(@PathVariable Long id, @PathVariable String firstName){
        return studentService.updateFirstName(firstName, id) + " Student(s) updated";
    }

    @DeleteMapping("deleteByFirstName/{firstName}")
    public String deleteByFirstName(@PathVariable String firstName){
        return studentService.deleteByFirstName(firstName) + " Student(s) deleted";
    }

    @GetMapping("getByCity/{city}")
    public List<StudentResponse> getByCity(@PathVariable String city){
        List <Student> studentList = studentService.getByCity(city);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }
}
