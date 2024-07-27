package pl.covidSimulation.service.imp;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.covidSimulation.dto.simulation.SimulationCreateDataDto;
import pl.covidSimulation.dto.simulation.SimulationReadDto;
import pl.covidSimulation.entity.SimulationData;
import pl.covidSimulation.exception.simulateData.SimulationNotFoundException;
import pl.covidSimulation.repository.SimulationDataRepository;
import pl.covidSimulation.service.PopulationService;
import pl.covidSimulation.service.SimulationServiceData;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SimulationServiceDataImp implements SimulationServiceData {

    private final SimulationDataRepository simulationDataRepository;
    private final PopulationService populationService;

    @Autowired
    public SimulationServiceDataImp(SimulationDataRepository simulationDataRepository, PopulationService populationService) {
        this.simulationDataRepository = simulationDataRepository;
        this.populationService = populationService;
    }

    @Override
    @Transactional
    public ResponseEntity<String> createSimulation(SimulationCreateDataDto simulationCreateDataDto) {

        SimulationData simulationData = new SimulationData(simulationCreateDataDto);
        simulationDataRepository.save(simulationData);
        populationService.createSimulationData(simulationData);

        return ResponseEntity.ok("Your data has been added");

    }

    @Override
    public List<SimulationReadDto> getAllSimulation() {
        return simulationDataRepository.findAll().stream().map(SimulationReadDto::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> deleteSimulation(Integer id) {
        simulationDataRepository.findById(id).orElseThrow(() -> new SimulationNotFoundException("simulation Not Found"));
        simulationDataRepository.deleteById(id);

        return ResponseEntity.ok("Simulation has been removed");

    }
}
