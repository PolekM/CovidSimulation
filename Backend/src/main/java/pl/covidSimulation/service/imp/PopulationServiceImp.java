package pl.covidSimulation.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.covidSimulation.repository.PopulationRepository;
import pl.covidSimulation.service.PopulationService;

@Service
public class PopulationServiceImp implements PopulationService {

    private final PopulationRepository populationRepository;

    @Autowired
    public PopulationServiceImp(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }
}
