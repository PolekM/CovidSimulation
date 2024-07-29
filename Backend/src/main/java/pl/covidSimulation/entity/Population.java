package pl.covidSimulation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer pi; //number of people infected
    private Integer pv; //number of healthy people susceptible to infection
    private Integer pm; // number of death people
    private Integer pr; // The number of people who have recovered and acquired immunity
    @ManyToOne
    @JoinColumn(name = "simulation_data_id")
    private SimulationData simulationData;


    public Population(SimulationData simulationData) {
        this.pi = simulationData.getI();
        this.pv = simulationData.getP() - simulationData.getI();
        this.pm = 0;
        this.pr = 0;
        this.simulationData = simulationData;
    }

    public Population(Population population) {
        this.pi = population.getPi();
        this.pv = population.getPv();
        this.pm = population.getPm();
        this.pr = population.getPr();
        this.simulationData = population.getSimulationData();
    }
}


