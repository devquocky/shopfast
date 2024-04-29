package personal.shopfast.dao.entity;

import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import personal.shopfast.util.annotation.PhoneNumber;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@EnableAutoConfiguration
@Table(name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "telephone")
        })
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "telephone")
    @PhoneNumber
    private String phoneNumber;

    @Column(name = "created_at")
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "modified_at")
    private LocalDateTime modifiedTime;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
