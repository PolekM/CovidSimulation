package pl.covidSimulation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.covidSimulation.dto.simulation.SimulationReadDto;
import pl.covidSimulation.dto.simulation.SimulationSaveDataDto;
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

    @GetMapping("")
    public List<SimulationReadDto> getAllSimulation() {
        return simulationServiceData.getAllSimulation();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSimulation(@Valid @RequestBody SimulationSaveDataDto simulationSaveDataDto) {
        return simulationServiceData.createSimulation(simulationSaveDataDto);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteSimulation(@PathVariable Integer id) {
        return simulationServiceData.deleteSimulation(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSimulationData(@PathVariable Integer id, @RequestBody SimulationSaveDataDto simulationSaveDataDto){
        return simulationServiceData.updateSimulationData(id,simulationSaveDataDto);
    }

}
