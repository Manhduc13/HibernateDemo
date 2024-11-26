package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "departments", schema = "dbo")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, columnDefinition = "nvarchar(50)", unique = true)
    String name;
    @Column(columnDefinition = "nvarchar(500)")
    String description;

    @OneToMany(mappedBy = "department")
    Set<Employee> employees;
}
