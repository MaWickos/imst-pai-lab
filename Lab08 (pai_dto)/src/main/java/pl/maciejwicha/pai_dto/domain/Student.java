package pl.maciejwicha.pai_dto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;

    @JoinColumn(name="address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Lob
    private byte[] attachment;
}
