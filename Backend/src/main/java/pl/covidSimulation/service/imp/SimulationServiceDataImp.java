package pl.covidSimulation.service.imp;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.covidSimulation.dto.simulation.SimulationCreateDataDto;
import pl.covidSimulation.entity.SimulationData;
import pl.covidSimulation.repository.SimulationDataRepository;
import pl.covidSimulation.service.SimulationServiceData;

@Service
@Slf4j
public class SimulationServiceDataImp implements SimulationServiceData {

    private final SimulationDataRepository simulationDataRepository;

    @Autowired
    public SimulationServiceDataImp(SimulationDataRepository simulationDataRepository) {
        this.simulationDataRepository = simulationDataRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<String> createSimulation(SimulationCreateDataDto simulationCreateDataDto) {

            SimulationData simulationData = new SimulationData(simulationCreateDataDto);
            simulationDataRepository.save(simulationData);
            return ResponseEntity.ok("Your data has been added");

    }
}
