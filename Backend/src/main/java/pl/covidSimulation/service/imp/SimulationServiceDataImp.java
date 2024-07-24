package pl.covidSimulation.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.covidSimulation.repository.SimulationDataRepository;
import pl.covidSimulation.service.SimulationServiceData;

@Service
public class SimulationServiceDataImp implements SimulationServiceData {

    private final SimulationDataRepository simulationDataRepository;

    @Autowired
    public SimulationServiceDataImp(SimulationDataRepository simulationDataRepository) {
        this.simulationDataRepository = simulationDataRepository;
    }
}
