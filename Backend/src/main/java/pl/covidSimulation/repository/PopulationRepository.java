package pl.covidSimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.covidSimulation.entity.Population;

@Repository
public interface PopulationRepository extends JpaRepository<Population,Integer> {
}
