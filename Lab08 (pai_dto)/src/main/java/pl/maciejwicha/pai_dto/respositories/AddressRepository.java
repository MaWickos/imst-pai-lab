package pl.maciejwicha.pai_dto.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejwicha.pai_dto.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
