package pl.covidSimulation.service.imp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.covidSimulation.dto.simulation.Population.PopulationReadDto;
import pl.covidSimulation.entity.Population;
import pl.covidSimulation.entity.SimulationData;
import pl.covidSimulation.repository.PopulationRepository;
import pl.covidSimulation.service.PopulationService;

@Service
public class PopulationServiceImp implements PopulationService {

    private final PopulationRepository populationRepository;

    @Autowired
    public PopulationServiceImp(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    @Transactional
    public PopulationReadDto createPopulation(SimulationData simulationData){
        if(simulationData==null){
            throw new IllegalArgumentException();

        }
        populationRepository.save(new Population(simulationData));
        return new PopulationReadDto();

    }
}
