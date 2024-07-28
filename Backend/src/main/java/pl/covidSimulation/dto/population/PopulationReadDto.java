package pl.covidSimulation.dto.population;

import lombok.Getter;
import lombok.Setter;
import pl.covidSimulation.entity.Population;

@Getter
@Setter
public class PopulationReadDto {

    private Integer simulationId;
    private Integer pi;
    private Integer pv;
    private Integer pm;
    private Integer pr;

    public PopulationReadDto(Integer id, Population population){
        this.simulationId = id;
        this.pi = population.getPi();
        this.pv = population.getPv();
        this.pm = population.getPm();
        this.pr = population.getPr();
    }
}
