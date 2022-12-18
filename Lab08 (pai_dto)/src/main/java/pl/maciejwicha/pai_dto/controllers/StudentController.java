package pl.maciejwicha.pai_dto.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejwicha.pai_dto.domain.Student;
import pl.maciejwicha.pai_dto.dtos.StudentDto;
import pl.maciejwicha.pai_dto.services.StudentServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudent();
    }

    @GetMapping("/dto")
    public List<StudentDto> getAllStudentsWithDto(){
        return studentService.getAllStudentsDto();
    }

    @GetMapping("/no-attachments")
    public List<StudentDto> getAllStudentsNoAttachments(){
        return studentService.getAllStudentsNoAttachment();
    }

    @GetMapping("/with-mapper")
    public List<StudentDto> getAllStudentsWithMapper(){
        return studentService.getAllStudentsWithMapper();
    }
}
