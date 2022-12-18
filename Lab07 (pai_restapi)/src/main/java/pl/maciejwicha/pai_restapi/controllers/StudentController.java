package pl.maciejwicha.pai_restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.maciejwicha.pai_restapi.entities.Student;
import pl.maciejwicha.pai_restapi.services.StudentService;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAll(){
        return studentService.getStudentsList();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentService.getStudent(id);
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id){
        return studentService.deleteStudent(id);
    }

    @PutMapping("/edit/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody Student student){
        return studentService.editStudent(id, student);
    }
}
