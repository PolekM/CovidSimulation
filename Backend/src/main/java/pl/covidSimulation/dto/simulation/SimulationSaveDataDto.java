package pl.covidSimulation.dto.simulation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulationSaveDataDto {

    @NotNull
    private String n; // simulation name
    @NotNull
    private Integer p;
    @NotNull
    private Integer i; // initial number of infected
    @NotNull
    private Integer r; //An indicator of how many people one infected person infects,
    @NotNull
    @Min(0)
    @Max(1)
    private Double m; // mortality rate
    @NotNull
    private Integer ti; // The number of days that elapse between infection and recovery of the patient
    @NotNull
    private Integer tm; //The number of days that elapse between infection and the death of the patient
    @NotNull
    private Integer ts; // Number of days for which the simulation is to be carried out

    @Override
    public String toString() {
        return "SimulationSaveDataDto{" +
                "n='" + n + '\'' +
                ", p=" + p +
                ", i=" + i +
                ", r=" + r +
                ", m=" + m +
                ", ti=" + ti +
                ", tm=" + tm +
                ", ts=" + ts +
                '}';
    }
}
