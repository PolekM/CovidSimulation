package pl.covidSimulation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.covidSimulation.entity.Population;

import java.util.List;

@Repository
public interface PopulationRepository extends JpaRepository<Population,Integer> {

    List<Population> findAllBySimulationDataId(Integer id);
    void deleteAllBySimulationDataId(Integer id);

}
