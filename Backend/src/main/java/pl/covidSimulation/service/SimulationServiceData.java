package pl.covidSimulation.service;

import org.springframework.http.ResponseEntity;
import pl.covidSimulation.dto.simulation.SimulationCreateDataDto;
import pl.covidSimulation.dto.simulation.SimulationReadDto;

import java.util.List;

public interface SimulationServiceData {
    ResponseEntity<String> createSimulation(SimulationCreateDataDto simulationCreateDataDto);
    List<SimulationReadDto> getAllSimulation();
}
