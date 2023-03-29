package com.learning.LearningOne.Service;

import com.learning.LearningOne.Model.Student;
import com.learning.LearningOne.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    public void saveStudent(Student student) {
        Optional<Student> student1 = studentRepo.findStudentByEmail(student.getEmail());
        if(student1.isPresent()){
            throw new IllegalStateException("Student with this email already exists");
        }
        studentRepo.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public void updateStudent(Student student, Long id) {
        Student student1 = studentRepo.findById(id).get();
        if(!ObjectUtils.isEmpty(student1)){
            if(StringUtils.hasText(student.getName())){
               student1.setName(student.getName());
            }
            if(StringUtils.hasText(student.getEmail())){
                student1.setEmail(student.getEmail());
            }
            if(StringUtils.hasText(student.getDob().toString())){
                student1.setDob(LocalDate.parse(student.getDob().toString()));
            }
            if(StringUtils.hasText(student.getAge().toString())){
                student1.setAge(student.getAge());
            }
        }
        else {
            throw new IllegalStateException("Doesn't found the student with the id "+id+" So, unable to update");
        }
         studentRepo.save(student1);
    }

    public void deleteStudentById(Long id) {
        boolean exists = studentRepo.existsById(id);
        if(!exists){
            throw new IllegalStateException("Student with id "+id+" doesn't exists");
        }
        studentRepo.deleteById(id);
    }
}
