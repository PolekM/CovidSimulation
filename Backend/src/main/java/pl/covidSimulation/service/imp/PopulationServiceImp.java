package pl.covidSimulation.service.imp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.covidSimulation.entity.Population;
import pl.covidSimulation.entity.SimulationData;
import pl.covidSimulation.repository.PopulationRepository;
import pl.covidSimulation.service.PopulationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PopulationServiceImp implements PopulationService {

    private final PopulationRepository populationRepository;

    @Autowired
    public PopulationServiceImp(PopulationRepository populationRepository) {
        this.populationRepository = populationRepository;
    }

    @Transactional
    public void createSimulationData(SimulationData simulationData) {

        List<Population> covidSimulationDataList = new ArrayList<>();

        Integer[] newInfection = new Integer[simulationData.getTs()];
        Integer[] newRecoveries = new Integer[simulationData.getTs()];
        Integer[] newDeaths = new Integer[simulationData.getTs()];
        fillArrays(newInfection, newRecoveries, newDeaths);

        initFirstPopulation(simulationData, covidSimulationDataList, newInfection);
        createAllDay(newInfection, newDeaths, newRecoveries, simulationData, covidSimulationDataList);
        populationRepository.saveAll(covidSimulationDataList);

    }

    public void fillArrays(Integer[] newInfection, Integer[] newRecoveries, Integer[] newDeaths) {
        Arrays.fill(newInfection, 0);
        Arrays.fill(newRecoveries, 0);
        Arrays.fill(newDeaths, 0);
    }

    public void initFirstPopulation(SimulationData simulationData, List<Population> covidSimulationDataList, Integer[] newInfection) {
        Population firstPopulation = new Population(simulationData);
        covidSimulationDataList.add(firstPopulation);
        newInfection[0] = firstPopulation.getPi();
    }

    public void createAllDay(Integer[] newInfection, Integer[] newDeaths, Integer[] newRecoveries, SimulationData simulationData, List<Population> covidSimulationDataList) {
        for (int i = 1; i < simulationData.getTs(); i++) {
            Population currentDate = new Population(covidSimulationDataList.get(i - 1));
            if (simulationData.getTm() <= i) {
                newDeadPeople(i, newInfection, newDeaths, simulationData, currentDate);
            }
            if (simulationData.getTi() <= i) {
                newRecoveredPeople(newInfection, i, simulationData, newRecoveries, currentDate);
            }
            newInfectedPeople(currentDate, simulationData, newInfection, i);
            covidSimulationDataList.add(currentDate);

        }
    }

    public void newDeadPeople(Integer i, Integer[] newInfection, Integer[] newDeaths, SimulationData simulationData, Population currentDate) {

        int infectedThatDay = newInfection[i - simulationData.getTm()];
        int currentDead = (int) (infectedThatDay * simulationData.getM());
        newDeaths[i] = currentDead;
        currentDate.setPm(currentDate.getPm() + currentDead);
        currentDate.setPi(currentDate.getPi() - currentDead);

    }

    public void newRecoveredPeople(Integer[] newInfection, Integer i, SimulationData simulationData, Integer[] newRecoveries, Population currentDate) {
        int infectedThatDay = newInfection[i - simulationData.getTi()];
        int currentHeal = (int) (infectedThatDay - infectedThatDay * simulationData.getM());
        newRecoveries[i] = currentHeal;
        currentDate.setPr(currentHeal + currentDate.getPr());
        currentDate.setPi(currentDate.getPi() - currentHeal);
    }

    public void newInfectedPeople(Population currentDate, SimulationData simulationData, Integer[] newInfection, Integer i) {
        int currentInfected = Math.min(currentDate.getPv(), currentDate.getPi() * simulationData.getR());
        currentDate.setPi(currentInfected + currentDate.getPi());
        currentDate.setPv(currentDate.getPv() - currentInfected);
        newInfection[i] = currentInfected;
    }


}
