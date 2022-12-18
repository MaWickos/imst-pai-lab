package pl.pollub.mw.pai_spring.dao;

import pl.pollub.mw.pai_spring.beans.Pracownik;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PracownikDAO {
    JdbcTemplate jdbcTemplate;
    
    public void setTemplate(JdbcTemplate jdbcTemplate){
        // Wstrzyknięcie przez metode SET
        this.jdbcTemplate = jdbcTemplate; 
    }
    
    public int save(Pracownik pracownik){
        String sql = "INSERT INTO pracownik (nazwisko, pensja, firma) values('"
                    + pracownik.getNazwisko() + "'," 
                    + "'" + Float.toString(pracownik.getPensja()) + "', "
                    + "'" + pracownik.getFirma() + "')";
        return jdbcTemplate.update(sql);
    }
    
    public List<Pracownik> getAll(){
        return jdbcTemplate.query(
            "SELECT * FROM pracownik", 
                new RowMapper<Pracownik>(){
                    @Override
                    public Pracownik mapRow(ResultSet resultSet, int row) throws SQLException{
                        Pracownik pracownik = new Pracownik();
                        
                        pracownik.setId(resultSet.getInt(1));
                        pracownik.setNazwisko(resultSet.getString(2));
                        pracownik.setPensja(resultSet.getFloat(3));
                        pracownik.setFirma(resultSet.getString(4));
                        
                       return pracownik;
                    }
                });
    }
    
    // Zadanie 4.4
    public void delete(int id){
        String sql = "DELETE FROM pracownik WHERE id = " + Integer.toString(id);
        jdbcTemplate.execute(sql);
    }
    
    // Zadanie 4.5
    public int update(Pracownik pracownik){
               String sql = "UPDATE pracownik SET "
                       + "nazwisko = '" + pracownik.getNazwisko() + "', "
                       + "pensja = '" + Float.toString(pracownik.getPensja()) + "', "
                       + "firma = '" + pracownik.getFirma() + "' "
                       + "WHERE id = " + Integer.toString(pracownik.getId());
        return jdbcTemplate.update(sql);
    } 
    
    public Pracownik getPracownikById(int id){
        String sql = "SELECT * FROM pracownik where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, 
                new Object[]{id},
                new BeanPropertyRowMapper<>(Pracownik.class));
        } catch(Exception ex){
            System.out.println(ex);
            return null;
        }

    }
    
}
