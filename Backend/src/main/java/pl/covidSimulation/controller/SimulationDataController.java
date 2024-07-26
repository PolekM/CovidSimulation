package pl.covidSimulation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.covidSimulation.dto.simulation.SimulationCreateDataDto;
import pl.covidSimulation.dto.simulation.SimulationReadDto;
import pl.covidSimulation.service.SimulationServiceData;

import java.util.List;

@RestController
@RequestMapping("/simulation")
public class SimulationDataController {

    private final SimulationServiceData simulationServiceData;

    @Autowired
    public SimulationDataController(SimulationServiceData simulationServiceData) {
        this.simulationServiceData = simulationServiceData;
    }

    @GetMapping("/all")
    public List<SimulationReadDto> getAllSimulation(){
        return simulationServiceData.getAllSimulation();
    }
    @PostMapping("/create")
    public ResponseEntity<String> createSimulation(@Valid @RequestBody SimulationCreateDataDto simulationCreateDataDto){
        return simulationServiceData.createSimulation(simulationCreateDataDto);
    }


}
