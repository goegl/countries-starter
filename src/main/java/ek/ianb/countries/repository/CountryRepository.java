package ek.ianb.countries.repository;

import ek.ianb.countries.model.Country;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CountryRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Country> rowMapper = (rs, rowNum) -> {
        Country country = new Country(rs.getInt("id"), rs.getString("name"));
//        country.setId(rs.getInt("id"));
//        country.setName(rs.getString("name"));
        return country;
    };

//    public List<Country> findAll() {
//        String sql = "SELECT id, name FROM countries";
//        return jdbcTemplate.query(sql, new CountryRowMapper());
//    }

    public List<Country> findAll(){
        String sql = "SELECT id, name FROM countries";
        return jdbcTemplate.query(sql, rowMapper);
    }
}




