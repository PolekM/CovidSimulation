package pl.covidSimulation.service;

import org.springframework.http.ResponseEntity;
import pl.covidSimulation.dto.simulation.SimulationSaveDataDto;
import pl.covidSimulation.dto.simulation.SimulationReadDto;

import java.util.List;

public interface SimulationServiceData {
    ResponseEntity<String> createSimulation(SimulationSaveDataDto simulationSaveDataDto);

    List<SimulationReadDto> getAllSimulation();

    ResponseEntity<String> deleteSimulation(Integer id);

    ResponseEntity<String> updateSimulationData(Integer id, SimulationSaveDataDto simulationSaveDataDto);
}
