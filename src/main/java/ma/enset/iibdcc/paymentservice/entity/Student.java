package ma.enset.iibdcc.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Student {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String code;
    private String sector;
    private String picture;
    @OneToMany(mappedBy = "student")
    private List<Payment> payments;

}
