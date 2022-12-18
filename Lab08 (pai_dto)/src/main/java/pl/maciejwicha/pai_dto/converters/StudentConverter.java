package pl.maciejwicha.pai_dto.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.maciejwicha.pai_dto.domain.Student;
import pl.maciejwicha.pai_dto.dtos.StudentDto;

@Component
public class StudentConverter implements Converter<Student, StudentDto> {

    @Override
    public StudentDto convert(Student source) {
        return StudentDto.builder()
                .name(source.getName())
                .surname(source.getSurname())
                .age(source.getAge())
                .state(source.getAddress().getState())
                .city(source.getAddress().getCity())
                .street(source.getAddress().getStreet())
                .zip(source.getAddress().getZip())
                .build();
    }
}
