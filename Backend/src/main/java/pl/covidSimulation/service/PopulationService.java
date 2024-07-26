package pl.covidSimulation.service;

import pl.covidSimulation.dto.population.PopulationReadDto;
import pl.covidSimulation.entity.SimulationData;

public interface PopulationService {

    PopulationReadDto createSimulationData(SimulationData simulationData);
}
