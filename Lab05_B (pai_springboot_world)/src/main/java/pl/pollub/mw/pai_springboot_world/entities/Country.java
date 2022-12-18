package pl.pollub.mw.pai_springboot_world.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
    
    @Id
    private String code;
    private String name;
    private String continent;
    private Double population;
    private Double surfaceArea;
    
    @Override
    public String toString() {
        return "Country{" +
                "code = '" + code + '\'' +
                ", name = '" + name + '\'' +
                ", continent = '" + continent + '\'' +
                ", population = " + population +
                ", surfaceArea = " + surfaceArea +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Double getPopulation() {
        return population;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }
    
}
