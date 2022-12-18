package pl.maciejwicha.pai_dto.services;

import pl.maciejwicha.pai_dto.domain.Student;
import pl.maciejwicha.pai_dto.dtos.StudentDto;

import java.util.List;

public interface StudentService {

    public List<Student> getAllStudent();
    public List<StudentDto> getAllStudentsDto();
    public List<StudentDto> getAllStudentsNoAttachment();
    public List<StudentDto> getAllStudentsWithMapper();
}
