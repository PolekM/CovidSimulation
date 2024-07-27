package pl.covidSimulation.dto.simulation;

import lombok.Getter;
import lombok.Setter;
import pl.covidSimulation.entity.SimulationData;

@Getter
@Setter
public class SimulationReadDto {

    private Integer id;
    private String n;
    private Integer p;
    private Integer i;
    private Integer r;
    private Double m;
    private Integer ti;
    private Integer tm;
    private Integer ts;

    public SimulationReadDto(SimulationData simulationData){
        this.n = simulationData.getN();
        this.p = simulationData.getP();
        this.i = simulationData.getI();
        this.r = simulationData.getR();
        this.m = simulationData.getM();
        this.ti = simulationData.getTi();
        this.tm = simulationData.getTm();
        this.ts = simulationData.getTs();
        this.id = simulationData.getId();
    }
}
