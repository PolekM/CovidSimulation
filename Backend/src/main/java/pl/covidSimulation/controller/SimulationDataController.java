package pl.covidSimulation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.covidSimulation.dto.simulation.SimulationCreateDataDto;
import pl.covidSimulation.service.SimulationServiceData;

@RestController
@RequestMapping("/simulation")
public class SimulationDataController {

    private final SimulationServiceData simulationServiceData;

    @Autowired
    public SimulationDataController(SimulationServiceData simulationServiceData) {
        this.simulationServiceData = simulationServiceData;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSimulation(@Valid @RequestBody SimulationCreateDataDto simulationCreateDataDto){
        return simulationServiceData.createSimulation(simulationCreateDataDto);
    }

}
