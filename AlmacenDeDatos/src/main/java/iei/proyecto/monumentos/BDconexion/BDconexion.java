package iei.proyecto.monumentos.BDconexion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BDconexion {

    public BDconexion(){}

    // Definir el DataSource para la conexión con la base de datos
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://databaseiei.cn0ai6cmskk8.eu-west-3.rds.amazonaws.com:3306/IEI_database");
        dataSource.setUsername("admin");
        dataSource.setPassword("alcachofaIEI");
        return dataSource;
    }

    // Configurar JdbcTemplate para inyección
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
