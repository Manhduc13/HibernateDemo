package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "addresses", schema = "dbo")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @Column(name = "employeeID")
    int employeeID;
    String street;
    String city;
    String country;
    @OneToOne
    @MapsId
    @JoinColumn(name = "employeeId")
    Employee employee;
}
