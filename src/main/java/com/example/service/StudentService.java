package com.example.service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getAllStudents(){
       return studentRepository.findAll();
    }

    public Student createStudent (CreateStudentRequest createStudentRequest){
        Student student = new Student(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        student.setAddress(address);
        student =  studentRepository.save(student);

        List<Subject> subjectList = new ArrayList<Subject>();
        if(createStudentRequest.getSubjectsLearning() != null){
            for(CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectsLearning()){
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained((createSubjectRequest.getMarksObtained()));
                subject.setStudent(student);

                subjectList.add(subject);
            }
            subjectRepository.saveAll(subjectList);
        }
        student.setLearningSubjects(subjectList);
        return student;
    }

    public Student updateStudent (UpdateStudentRequest updateStudentRequest){
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()){
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        if (updateStudentRequest.getLastName() != null && !updateStudentRequest.getLastName().isEmpty()) {
            student.setLastName(updateStudentRequest.getLastName());
        }
        if (updateStudentRequest.getEmail() != null && !updateStudentRequest.getEmail().isEmpty()) {
            student.setEmail(updateStudentRequest.getEmail());
        }

        student = studentRepository.save(student);
        return student;
    }

    public String deleteStudent(Long id){
        studentRepository.deleteById(id);
        return "Student Deleted Successfully";
    }

    public List<Student> getByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getByFirstNameAndLastName(String firstName, String lastName){
//        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
          return studentRepository.getByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameOrLastName(String firstName, String lastName){
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest){
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    public List<Student> getAllWithPagination(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getAllWithSorting(){
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName");

        return studentRepository.findAll(sort);
    }

    public List<Student> like(String firstName){
        return studentRepository.findByFirstNameContains(firstName);
    }

    public List<Student> startsWith(String firstName){
        return studentRepository.findByFirstNameStartingWith(firstName);
    }

    public Integer updateFirstName(String firstName, Long id){
        return studentRepository.updateFirstName(firstName, id);
    }

    public Integer deleteByFirstName(String firstName){
        return studentRepository.deleteByFirstName(firstName);
    }

    public List<Student> getByCity(String city){
//        return studentRepository.findByAddressCity(city);
        return studentRepository.getByAddressCity(city);

    }
}
