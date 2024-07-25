package pl.covidSimulation.service;

import pl.covidSimulation.dto.simulation.Population.PopulationReadDto;
import pl.covidSimulation.entity.SimulationData;

public interface PopulationService {

    PopulationReadDto createPopulation(SimulationData simulationData);
}
