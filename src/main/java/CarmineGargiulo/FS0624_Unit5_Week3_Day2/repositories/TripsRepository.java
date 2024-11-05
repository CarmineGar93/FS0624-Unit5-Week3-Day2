package CarmineGargiulo.FS0624_Unit5_Week3_Day2.repositories;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripsRepository extends JpaRepository<Trip, UUID> {

}
