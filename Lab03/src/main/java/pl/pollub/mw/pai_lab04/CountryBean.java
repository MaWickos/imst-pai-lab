package pl.pollub.mw.pai_lab04;

import java.io.Serializable;

public class CountryBean implements Serializable{
    private String code;
    private String name;
    private Integer population;
    private String continent;
    private String region;
    private Float SurfacaArea;
    private Integer indepYear;
    private String governmentForm;
    private String headOfState;

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurfacaArea() {
        return SurfacaArea;
    }

    public void setSurfacaArea(Float SurfacaArea) {
        this.SurfacaArea = SurfacaArea;
    }

    public Integer getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }
    
    
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
    
    
    
}
