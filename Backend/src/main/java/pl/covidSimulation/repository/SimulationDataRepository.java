package pl.covidSimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.covidSimulation.entity.SimulationData;

@Repository
public interface SimulationDataRepository extends JpaRepository<SimulationData, Integer> {
}
