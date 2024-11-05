package CarmineGargiulo.FS0624_Unit5_Week3_Day2.repositories;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeesRepository extends JpaRepository<Employee, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
}
