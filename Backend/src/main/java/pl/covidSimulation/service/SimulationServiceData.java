package pl.covidSimulation.service;

import org.springframework.http.ResponseEntity;
import pl.covidSimulation.dto.simulation.SimulationCreateDataDto;

public interface SimulationServiceData {
    ResponseEntity<String> createSimulation(SimulationCreateDataDto simulationCreateDataDto);
}
