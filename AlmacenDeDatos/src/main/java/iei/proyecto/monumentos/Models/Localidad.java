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

    @Column(name="direccion", length=100, nullable=false)
    private String direccion;

    @Column(name="codigo_postal", nullable=false)
    private int codigoPostal;

    @Column(name="longitud", nullable=false)
    private int longitud;

    @Column(name="latitud", nullable=false)
    private int latitud;

    Column(name="descripcion", length=40, nullable=false)
    private String descripcion;

    public Tipo enum('Yacimiento arqueológico','Iglesia-Ermita','Monasterio-Convento','Castillo-Fortaleza-Torre','Edificio singular','Puente','Otros')
    Column(name="tipo", nullable=false)
    private Tipo tipo;

    @Column(name="localidad_id", nullable=false)
    private int localidadId;

}
