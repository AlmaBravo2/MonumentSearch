package iei.proyecto.monumentos.BDconexion;

import iei.proyecto.monumentos.Models.Localidad;
import iei.proyecto.monumentos.Models.Provincia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalidadBD {

    private final JdbcTemplate jdbcTemplate;

    // Constructor para inyectar JdbcTemplate
    public LocalidadBD(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Inserta una localidad
    public void insertarLocalidad(Localidad localidad) {
        String sql = "INSERT INTO Localidad (codigo, nombre, provincia_codigo) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, localidad.getCodigo(), localidad.getNombre(), localidad.getProvincia().getCodigo());
        System.out.println("Localidad insertada con éxito: " + localidad.getNombre());
    }

    // Consulta una localidad por su código
    public Localidad consultarLocalidadPorCodigo(int codigo) {
        String sql = "SELECT l.codigo, l.nombre, l.provincia_codigo, p.nombre as provincia_nombre " +
                "FROM Localidad l JOIN Provincia p ON l.provincia_codigo = p.codigo WHERE l.codigo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codigo}, new RowMapper<Localidad>() {
            @Override
            public Localidad mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new Localidad(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        new Provincia(rs.getInt("provincia_codigo"), rs.getString("provincia_nombre"))
                );
            }
        });
    }

    // Consulta todas las localidades
    public List<Localidad> consultarTodasLasLocalidades() {
        String sql = "SELECT l.codigo, l.nombre, l.provincia_codigo, p.nombre as provincia_nombre " +
                "FROM Localidad l JOIN Provincia p ON l.provincia_codigo = p.codigo";
        return jdbcTemplate.query(sql, new RowMapper<Localidad>() {
            @Override
            public Localidad mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new Localidad(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        new Provincia(rs.getInt("provincia_codigo"), rs.getString("provincia_nombre"))
                );
            }
        });
    }

    // Actualiza los detalles de una localidad
    public void actualizarLocalidad(Localidad localidad) {
        String sql = "UPDATE Localidad SET nombre = ?, provincia_codigo = ? WHERE codigo = ?";
        jdbcTemplate.update(sql, localidad.getNombre(), localidad.getProvincia().getCodigo(), localidad.getCodigo());
        System.out.println("Localidad actualizada con éxito: " + localidad.getNombre());
    }

    // Elimina una localidad
    public void eliminarLocalidad(int codigo) {
        String sql = "DELETE FROM Localidad WHERE codigo = ?";
        jdbcTemplate.update(sql, codigo);
        System.out.println("Localidad eliminada con éxito: " + codigo);
    }
}
