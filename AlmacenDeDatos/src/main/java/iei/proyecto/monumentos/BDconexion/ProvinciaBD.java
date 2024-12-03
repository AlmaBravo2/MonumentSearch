package iei.proyecto.monumentos.BDconexion;

import iei.proyecto.monumentos.Models.Provincia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProvinciaBD {

    private final JdbcTemplate jdbcTemplate;

    // Constructor para inyectar JdbcTemplate
    public ProvinciaBD(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Inserta una provincia
    public void insertarProvincia(Provincia provincia) {
        String sql = "INSERT INTO Provincia (codigo, nombre) VALUES (?, ?)";
        jdbcTemplate.update(sql, provincia.getCodigo(), provincia.getNombre());
        System.out.println("Provincia insertada con éxito: " + provincia.getNombre());
    }

    // Consulta una provincia por su código
    public Provincia consultarProvinciaPorCodigo(int codigo) {
        String sql = "SELECT codigo, nombre FROM Provincia WHERE codigo = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{codigo}, new RowMapper<Provincia>() {
            @Override
            public Provincia mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new Provincia(rs.getInt("codigo"), rs.getString("nombre"));
            }
        });
    }

    // Consulta todas las provincias
    public List<Provincia> consultarTodasLasProvincias() {
        String sql = "SELECT codigo, nombre FROM Provincia";
        return jdbcTemplate.query(sql, new RowMapper<Provincia>() {
            @Override
            public Provincia mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new Provincia(rs.getInt("codigo"), rs.getString("nombre"));
            }
        });
    }

    // Actualiza los detalles de una provincia
    public void actualizarProvincia(Provincia provincia) {
        String sql = "UPDATE Provincia SET nombre = ? WHERE codigo = ?";
        jdbcTemplate.update(sql, provincia.getNombre(), provincia.getCodigo());
        System.out.println("Provincia actualizada con éxito: " + provincia.getNombre());
    }

    // Elimina una provincia
    public void eliminarProvincia(int codigo) {
        String sql = "DELETE FROM Provincia WHERE codigo = ?";
        jdbcTemplate.update(sql, codigo);
        System.out.println("Provincia eliminada con éxito: " + codigo);
    }
}