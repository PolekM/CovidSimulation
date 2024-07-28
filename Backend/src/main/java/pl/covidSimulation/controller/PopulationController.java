package pl.covidSimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.covidSimulation.dto.population.PopulationReadDto;
import pl.covidSimulation.service.PopulationService;

import java.util.List;

@RestController
@RequestMapping("/population")
public class PopulationController {

    private final PopulationService populationService;

    @Autowired
    public PopulationController(PopulationService populationService) {
        this.populationService = populationService;
    }

    @GetMapping("/{id}")
    public List<PopulationReadDto>getPopulationBySimulationId(@PathVariable Integer id){
        return populationService.getPopulationBySimulationId(id);
    }
}
