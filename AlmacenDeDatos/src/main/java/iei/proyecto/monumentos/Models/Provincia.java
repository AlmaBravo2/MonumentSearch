import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "Provincia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Provincia {

    @Id
    @Column(name="codigo")
    private int codigo;

    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

}
