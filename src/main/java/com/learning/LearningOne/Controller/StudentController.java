package com.learning.LearningOne.Controller;

import com.learning.LearningOne.Model.Student;
import com.learning.LearningOne.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void saveStudent(@RequestBody Student student) throws Exception {
         studentService.saveStudent(student);
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id){
         return studentService.getStudentById(id);
    }

    @PatchMapping("/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable Long id) throws Exception {
        studentService.updateStudent(student,id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
    }
}
