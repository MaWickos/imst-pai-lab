package pl.maciejwicha.pai_restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejwicha.pai_restapi.entities.Student;
import pl.maciejwicha.pai_restapi.entities.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentsList(){
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudent(Integer id){
        if(studentRepository.existsById(id)){
            return studentRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public String addStudent(Student student){
        studentRepository.save(student);
        return "Dodano studenta do bazy danych!";
    }

    public String deleteStudent(Integer id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student o ID " + Integer.toString(id) + " usuniety prawidlowo!";
        } else {
            return "Brak studenta o podanym ID!";
        }
    }

    public String editStudent(Integer id, Student student){
        if(studentRepository.existsById(id)){
            student.setId(id);
            studentRepository.save(student);
            return "Zmodyfikowane dane studenta o ID " + Integer.toString(id);
        } else {
            return "Brak studenta o podanym ID";
        }
    }
}
