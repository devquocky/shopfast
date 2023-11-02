package personal.shopfast.dao.entity;

import lombok.*;
import personal.shopfast.util.annotation.PhoneNumber;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "customer", schema = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "username")
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
