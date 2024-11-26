package fa.training.entities;

import fa.training.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "projects", schema = "dbo")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int project_id;

    @Column(nullable = false, columnDefinition = "nvarchar(100)")
    String project_name;

    @Column(nullable = false, columnDefinition = "nvarchar(100)", unique = true)
    String project_code;

    @Column(columnDefinition = "nvarchar(500)")
    String description;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime start_at;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime end_at;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime insert_at;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime update_at;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime delete_at;

    @Column(nullable = false)
    boolean isActive;

    @Column(nullable = false)
    ProjectStatus status;

//    @ManyToMany(mappedBy = "projects")
//    Set<Employee> employees;

    @OneToMany(mappedBy = "project")
    Set<EmployeeProject> employeeProjects;
}
