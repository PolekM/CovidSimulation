package pl.covidSimulation.service.imp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.covidSimulation.dto.simulation.SimulationReadDto;
import pl.covidSimulation.dto.simulation.SimulationSaveDataDto;
import pl.covidSimulation.entity.SimulationData;
import pl.covidSimulation.exception.simulateData.SimulationNotFoundException;
import pl.covidSimulation.repository.SimulationDataRepository;
import pl.covidSimulation.service.PopulationService;
import pl.covidSimulation.service.SimulationServiceData;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public ResponseEntity<String> createSimulation(SimulationSaveDataDto simulationSaveDataDto) {

        SimulationData simulationData = new SimulationData(simulationSaveDataDto);
        simulationDataRepository.save(simulationData);
        populationService.createSimulationData(simulationData);

        return ResponseEntity.ok("Your data has been added");

    }

    @Override
    @Transactional
    public ResponseEntity<String> updateSimulationData(Integer id, SimulationSaveDataDto simulationSaveDataDto) {
        SimulationData simulationData = simulationDataRepository.findById(id).orElseThrow(() -> new SimulationNotFoundException("Simulation does not exist "));
        simulationData.updateData(simulationSaveDataDto);
        populationService.updatePopulation(id, simulationData);

        simulationDataRepository.save(simulationData);
        return ResponseEntity.ok("Your data has been updated");
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
