package fa.training.entities;

import fa.training.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.ZonedDateTime;

@Entity
@Table(name = "employee_project", schema = "dbo")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeProject {
    @EmbeddedId
    EnrollmentID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    Project project;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime start_at;

    @TimeZoneStorage(TimeZoneStorageType.NATIVE)
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    ZonedDateTime end_at;

    ProjectRole project_role;
}
