package pl.covidSimulation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.covidSimulation.dto.simulation.SimulationSaveDataDto;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SimulationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String n; // simulation name
    private Integer p;
    private Integer i; // initial number of infected
    private Integer r; //An indicator of how many people one infected person infects,
    private Double m; // mortality rate
    private Integer ti; // The number of days that elapse between infection and recovery of the patient
    private Integer tm; //The number of days that elapse between infection and the death of the patient
    private Integer ts; // Number of days for which the simulation is to be carried out
    @OneToMany(mappedBy = "simulationData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Population> population;

    public SimulationData(SimulationSaveDataDto simulationSaveDataDto) {
        this.n = simulationSaveDataDto.getN();
        this.p = simulationSaveDataDto.getP();
        this.i = simulationSaveDataDto.getI();
        this.r = simulationSaveDataDto.getR();
        this.m = simulationSaveDataDto.getM();
        this.ti = simulationSaveDataDto.getTi();
        this.tm = simulationSaveDataDto.getTm();
        this.ts = simulationSaveDataDto.getTs();
    }

    public SimulationData updateData(SimulationSaveDataDto simulationSaveDataDto) {
        this.n = simulationSaveDataDto.getN();
        this.p = simulationSaveDataDto.getP();
        this.i = simulationSaveDataDto.getI();
        this.r = simulationSaveDataDto.getR();
        this.m = simulationSaveDataDto.getM();
        this.ti = simulationSaveDataDto.getTi();
        this.tm = simulationSaveDataDto.getTm();
        this.ts = simulationSaveDataDto.getTs();
        return this;
    }
}
