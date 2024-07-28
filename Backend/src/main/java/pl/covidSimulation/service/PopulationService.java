package pl.covidSimulation.service;

import pl.covidSimulation.dto.population.PopulationReadDto;
import pl.covidSimulation.entity.SimulationData;

import java.util.List;

public interface PopulationService {

    void createSimulationData(SimulationData simulationData);

    List<PopulationReadDto> getPopulationBySimulationId(Integer id);
}
