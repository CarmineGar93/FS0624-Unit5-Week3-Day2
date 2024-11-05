package CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.enums.TripStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    @Column(name = "trip_id")
    @Setter(AccessLevel.NONE)
    private UUID tripId;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus status;
    @OneToOne(mappedBy = "trip", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private Booking booking;

    public Trip(String destination) {
        this.destination = destination;
        this.status = TripStatus.IN_PROGRAM;
    }

    @Override
    public String toString() {
        return "Trip = id: " + tripId +
                ", destination: " + destination +
                ", date: " + date +
                ", status: " + status;
    }
}
