package NtiTeamTest.Repository;

import NtiTeamTest.Entity.Lord;
import NtiTeamTest.Entity.Planet;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LordRepositoryTest {
    @Autowired
    LordRepository lordRepository;
    @Autowired
    PlanetRepository planetRepository;

    @Before
    public void clearDB() {
        planetRepository.deleteAll();
        lordRepository.deleteAll();
    }

    void initDataTopTenYoungestLords() {
        Lord one = new Lord();
        one.setAge(1);
        one.setName("One");
        Lord three = new Lord();
        three.setAge(3);
        three.setName("Three");
        Lord two = new Lord();
        two.setAge(2);
        two.setName("Two");
        Lord four = new Lord();
        four.setAge(4);
        four.setName("Four");
        lordRepository.saveAll(Arrays.asList(one, three, two, four));

    }

    void initDataForLordsWithOutPlanets() {

        Lord lordWithPlanet = new Lord();
        lordWithPlanet.setAge(1);
        lordWithPlanet.setName("lordWithPlanet");

        Lord lordWithOutPlanet = new Lord();
        lordWithOutPlanet.setAge(11);
        lordWithOutPlanet.setName("lordWithOutPlanet");

        Planet planet = new Planet("One");
        planet.setLord(lordWithPlanet);
        Planet planetTwo = new Planet("Two");
        planetTwo.setLord(lordWithPlanet);
        lordRepository.save(lordWithPlanet);
        lordRepository.save(lordWithOutPlanet);
        planetRepository.save(planet);
        planetRepository.save(planetTwo);

    }

    @Test
    void findAllLordsWhoNotRuleAnyPlanets() {
        clearDB();
        initDataForLordsWithOutPlanets();
        List<Lord> allLordsWhoNotRuleAnyPlanets = lordRepository.findAllLordsWhoNotRuleAnyPlanets();
        assertEquals(1, allLordsWhoNotRuleAnyPlanets.size());
        assertEquals("lordWithOutPlanet", allLordsWhoNotRuleAnyPlanets.get(0).getName());
    }

    @Test
    void topTenYoungestLords() {
        clearDB();
        initDataTopTenYoungestLords();
        List<Lord> lords = lordRepository.topTenYoungestLords(PageRequest.of(0, 3));
        int lordsSize = lords.size();
        assertEquals(3, lordsSize);
        assertNotEquals(10,3);
    }
}
