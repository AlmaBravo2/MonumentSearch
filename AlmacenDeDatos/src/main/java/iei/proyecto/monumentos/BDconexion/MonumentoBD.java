package iei.proyecto.monumentos.BDconexion;

import iei.proyecto.monumentos.Models.Monumento;
import iei.proyecto.monumentos.Models.Localidad;
import iei.proyecto.monumentos.Models.Tipo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MonumentoBD {

    private final JdbcTemplate jdbcTemplate;

    // Constructor para inyectar JdbcTemplate
    public MonumentoBD(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Inserta un monumento
    public void insertarMonumento(Monumento monumento) {
        String sql = "INSERT INTO Monumento (id, nombre, direccion, codigo_postal, longitud, latitud, descripcion, tipo, localidad_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                monumento.getId(),
                monumento.getNombre(),
                monumento.getDireccion(),
                monumento.getCodigoPostal(),
                monumento.getLongitud(),
                monumento.getLatitud(),
                monumento.getDescripcion(),
                monumento.getTipo().toString(),
                monumento.getLocalidad().getCodigo());
        System.out.println("Monumento insertado con éxito: " + monumento.getNombre());
    }

    // Consulta un monumento por su ID
    public Monumento consultarMonumentoPorId(int id) {
        String sql = "SELECT m.id, m.nombre, m.direccion, m.codigo_postal, m.longitud, m.latitud, m.descripcion, m.tipo, " +
                "l.codigo as localidad_codigo, l.nombre as localidad_nombre " +
                "FROM Monumento m JOIN Localidad l ON m.localidad_id = l.codigo WHERE m.id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Monumento>() {
            @Override
            public Monumento mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new Monumento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("codigo_postal"),
                        rs.getString("longitud"),
                        rs.getString("latitud"),
                        rs.getString("descripcion"),
                        Tipo.valueOf(rs.getString("tipo")),
                        new Localidad(rs.getInt("localidad_codigo"), rs.getString("localidad_nombre"), null)
                );
            }
        });
    }

    // Consulta todos los monumentos
    public List<Monumento> consultarTodosLosMonumentos() {
        String sql = "SELECT m.id, m.nombre, m.direccion, m.codigo_postal, m.longitud, m.latitud, m.descripcion, m.tipo, " +
                "l.codigo as localidad_codigo, l.nombre as localidad_nombre " +
                "FROM Monumento m JOIN Localidad l ON m.localidad_id = l.codigo";
        return jdbcTemplate.query(sql, new RowMapper<Monumento>() {
            @Override
            public Monumento mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new Monumento(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("codigo_postal"),
                        rs.getString("longitud"),
                        rs.getString("latitud"),
                        rs.getString("descripcion"),
                        Tipo.valueOf(rs.getString("tipo")),
                        new Localidad(rs.getInt("localidad_codigo"), rs.getString("localidad_nombre"), null)
                );
            }
        });
    }

    // Actualiza los detalles de un monumento
    public void actualizarMonumento(Monumento monumento) {
        String sql = "UPDATE Monumento SET nombre = ?, direccion = ?, codigo_postal = ?, longitud = ?, latitud = ?, " +
                "descripcion = ?, tipo = ?, localidad_id = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                monumento.getNombre(),
                monumento.getDireccion(),
                monumento.getCodigoPostal(),
                monumento.getLongitud(),
                monumento.getLatitud(),
                monumento.getDescripcion(),
                monumento.getTipo().toString(),
                monumento.getLocalidad().getCodigo(),
                monumento.getId());
        System.out.println("Monumento actualizado con éxito: " + monumento.getNombre());
    }

    // Elimina un monumento
    public void eliminarMonumento(int id) {
        String sql = "DELETE FROM Monumento WHERE id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Monumento eliminado con éxito: " + id);
    }
}
