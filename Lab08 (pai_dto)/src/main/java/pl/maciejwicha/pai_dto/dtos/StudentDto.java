package pl.maciejwicha.pai_dto.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String name;
    private String surname;
    private Integer age;
    private String street;
    private String city;
    private String zip;
    private String state;
}
