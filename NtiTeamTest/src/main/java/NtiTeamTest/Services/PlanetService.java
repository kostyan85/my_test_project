package NtiTeamTest.Services;

import NtiTeamTest.Entity.Planet;
import NtiTeamTest.Repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    /**
     * - Добавить новую Планету
     */
    public void addNewPlanet(Planet planet) {
        planetRepository.save(planet);
    }

    /**
     * - Уничтожить Планету
     */
    public void destroyPlanet(Long id) {
        planetRepository.deletePlanetById(id);
    }
}
