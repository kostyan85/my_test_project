package NtiTeamTest.Services;

import NtiTeamTest.Entity.Lord;
import NtiTeamTest.Entity.Planet;
import NtiTeamTest.Repository.LordRepository;
import NtiTeamTest.Repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LordService {

    @Autowired
    private LordRepository lordRepository;

    @Autowired
    private PlanetRepository planetRepository;

    /**
     * - Добавить нового Повелителя
     */
    public void addNewLord(Lord lord) {
        lordRepository.save(lord);
    }

    /**
     * - Назначить Повелителя управлять Планетой
     */
    public void appointLordRulePlanet(Long lordId, Long planetId) throws Exception {

        Optional<Planet> planetById = planetRepository.findById(planetId);
        Optional<Lord> lordById = lordRepository.findById(lordId);
        Planet planet = planetById.isPresent() ? planetById.get() : null;
        Lord lord = lordById.isPresent() ? lordById.get() : null;
        if (planet == null || lord == null) {
            throw new Exception("planet or lord is not present");
        }
        planet.setLord(lord);
        planetRepository.save(planet);

    }

    /**
     * - Найти всех Повелителей бездельников, которые прохлаждаются и не управляют никакими Планетами
     */
    public List<Lord> findAllLordsWhoNotRuleAnyPlanets() {

        return lordRepository.findAllLordsWhoNotRuleAnyPlanets();
    }

    /**
     * - Отобразить ТОП 10 самых молодых Повелителей
     */
    public List<Lord> topTenYoungestLords() {
        return lordRepository.topTenYoungestLords(PageRequest.of(0, 10));
    }
}
