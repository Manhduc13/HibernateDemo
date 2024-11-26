package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "employees", schema = "dbo")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int employee_id;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String firstName;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String lastName;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false, unique = true, length = 255)
    String phoneNumber;

    @Column(nullable = false, columnDefinition = "DATE")
    LocalDateTime hireDate;

    @Column(nullable = false)
    double salary;

    @Column(nullable = false, columnDefinition = "DATE")
    LocalDateTime dateOfBirth;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime insertedAt;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime updatedAt;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime deletedAt;

    @Column(nullable = false, columnDefinition = "bit")
    boolean isActive;

    @Transient
    String fullName;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Transient
    int age;

    public int getAge() {
        return Period.between(this.dateOfBirth.toLocalDate(), LocalDate.now()).getYears();
    }

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    Address address;

    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    Department department;

    //    @ManyToMany
//    @JoinTable(
//            name = "employee_project",
//            schema = "dbo",
//            joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "employee_id")},
//            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "project_id")}
//    )
//    Set<Project> projects;
    @OneToMany(mappedBy = "employee")
    Set<EmployeeProject> employeeProjects;
}
