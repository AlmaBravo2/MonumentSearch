import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "Localidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Localidad {

    @Id
    @Column(name="codigo")
    private int codigo;

    @Column(name="nombre", length=100, nullable=false)
    private String nombre;

    @Column(name="provincia_codigo", nullable=false)
    private int provinciaCodigo;

}
