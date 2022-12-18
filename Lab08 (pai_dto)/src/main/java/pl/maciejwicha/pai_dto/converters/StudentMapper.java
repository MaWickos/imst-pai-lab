package pl.maciejwicha.pai_dto.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.maciejwicha.pai_dto.domain.Student;
import pl.maciejwicha.pai_dto.dtos.StudentDto;

@Mapper
public interface StudentMapper {

    @Mapping(target = "name", source = "student.name")
    @Mapping(target = "surname", source = "student.surname")
    @Mapping(target = "age", source = "student.age")
    @Mapping(target = "street", source = "student.address.street")
    @Mapping(target = "city", source = "student.address.city")
    @Mapping(target = "state", source = "student.address.state")
    @Mapping(target = "zip", source = "student.address.zip")
    StudentDto mapStudentToStudentDto(Student student);

}
