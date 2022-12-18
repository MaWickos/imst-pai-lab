package pl.maciejwicha.pai_dto.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.maciejwicha.pai_dto.converters.StudentConverter;
import pl.maciejwicha.pai_dto.converters.StudentMapper;
import pl.maciejwicha.pai_dto.domain.Student;
import pl.maciejwicha.pai_dto.dtos.StudentDto;
import pl.maciejwicha.pai_dto.respositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentDto> getAllStudentsDto(){
        return (List<StudentDto>) studentRepository.findAll().stream()
                .map(studentConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllStudentsNoAttachment(){
        return studentRepository.findAllNoAttachment();
    }

    @Override
    public List<StudentDto> getAllStudentsWithMapper(){
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .collect(Collectors.toList());
    }
}