package pl.maciejwicha.pai_dto.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.maciejwicha.pai_dto.domain.Student;
import pl.maciejwicha.pai_dto.dtos.StudentDto;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT new pl.maciejwicha.pai_dto.dtos.StudentDto(s.name, s.surname, s.age, s.address.street, s.address.city, s.address.state, s.address.zip) from Student s")
    List<StudentDto> findAllNoAttachment();
}
