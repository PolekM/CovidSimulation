package pl.covidSimulation.service.imp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.covidSimulation.dto.population.PopulationReadDto;
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
    public PopulationReadDto createSimulationData(SimulationData simulationData){

        int days = simulationData.getTs();
        int daysToDead = simulationData.getTm();
        int daysToHeal = simulationData.getTi();

        List<Population> covidSimulationDataList = new ArrayList<>();

        Integer newInfection[] = new Integer[days];
        Integer newRecoveries[] = new Integer[days];
        Integer newDeaths[] = new Integer[days];

        Arrays.fill(newInfection,0);
        Arrays.fill(newRecoveries,0);
        Arrays.fill(newDeaths,0);

        Population initFirstPopulation = new Population(simulationData);
        covidSimulationDataList.add(initFirstPopulation);
        newInfection[0] = initFirstPopulation.getPi();
        System.out.println(0 + " zakazeni: " + initFirstPopulation.getPi()
                + " zdrowe: " + initFirstPopulation.getPv()
                + " martwe: " + initFirstPopulation.getPm()
                + " wyleczone: " + initFirstPopulation.getPr()
                + " newInfection: " + newInfection[0]
                + " newDeats: " + newDeaths[0]
                + " newRecovery: " + newRecoveries[0]
        );

        for(int i=1;i<days;i++){
            Population currentDate = new Population(covidSimulationDataList.get(i-1));
            if(daysToDead<=i){
                int infectedThatDay = newInfection[i-daysToDead];
                int currentDead = (int) (infectedThatDay * simulationData.getM());
                newDeaths[i] = currentDead;
                currentDate.setPm(currentDate.getPm() + currentDead);
                currentDate.setPi(currentDate.getPi() - currentDead);

            }
            if(daysToHeal<=i) {
                int infectedThatDay = newInfection[i-daysToHeal];
                int currentHeal = (int) (infectedThatDay - infectedThatDay * simulationData.getM());
                newRecoveries[i] = currentHeal;
                currentDate.setPr(currentHeal+currentDate.getPr());
                currentDate.setPi(currentDate.getPi()-currentHeal);
            }
            int currentInfected = Math.min(currentDate.getPv(),currentDate.getPi()*simulationData.getR());
            currentDate.setPi(currentInfected+currentDate.getPi());
            currentDate.setPv(currentDate.getPv()-currentInfected);
            newInfection[i] = currentInfected;
            System.out.println(i + " zakazeni: " + currentDate.getPi()
                    + " zdrowe: " + currentDate.getPv()
                    + " martwe: " + currentDate.getPm()
                    + " wyleczone: " + currentDate.getPr()
                    + " newInfection: " + newInfection[i]
                    + " newDeats: " + newDeaths[i]
                    + " newRecovery: " + newRecoveries[i]
            );
            covidSimulationDataList.add(currentDate);
        }

        return new PopulationReadDto();

    }


}
