package CarmineGargiulo.FS0624_Unit5_Week3_Day2.repositories;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Booking;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Employee;
import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, UUID> {
    boolean existsByTrip(Trip trip);

    Page<Booking> findByEmployee(Employee employee, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Booking b WHERE b.employee = :employee AND b" +
            ".trip.date = :date")
    boolean checkIfEmployeeIsNotAvailable(Employee employee, LocalDate date);
}
