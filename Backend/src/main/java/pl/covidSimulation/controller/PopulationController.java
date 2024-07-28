package pl.covidSimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.covidSimulation.dto.population.PopulationReadDto;
import pl.covidSimulation.service.PopulationService;

import java.util.List;

@RestController
@RequestMapping("/population")
@CrossOrigin(origins = "http://localhost:4200")
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
