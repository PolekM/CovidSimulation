package pl.covidSimulation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SimulationData {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String n; // simulation name
    private Integer i; // initial number of infected
    private Integer r; //An indicator of how many people one infected person infects,
    private Integer m; // mortality rate
    private Integer ti; // The number of days that elapse between infection and recovery of the patient
    private Integer tm; //The number of days that elapse between infection and the death of the patient
    private Integer ts; // Number of days for which the simulation is to be carried out
}
