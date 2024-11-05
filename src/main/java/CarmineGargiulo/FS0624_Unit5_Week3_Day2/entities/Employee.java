package CarmineGargiulo.FS0624_Unit5_Week3_Day2.entities;

import CarmineGargiulo.FS0624_Unit5_Week3_Day2.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
@JsonIgnoreProperties({"accountNonLocked", "accountNonExpired", "credentialsNonExpired", "enabled", "authorities"})
public class Employee implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    @Setter(AccessLevel.NONE)
    private UUID employeeId;
    @Column(nullable = false)
    private String username, name, surname, email, password;
    @Column(name = "avatar_url", nullable = false)
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private List<Booking> bookingList;

    public Employee(String username, String name, String surname, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.avatarUrl = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }

    @Override
    public String toString() {
        return "Employee " + employeeId +
                " = name: " + name +
                ", surname: " + surname +
                ", username: " + username +
                ", email: " + email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(getRole().name()));
    }

}
