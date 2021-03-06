package NtiTeamTest.Repository;

import NtiTeamTest.Entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlanetRepository extends JpaRepository<Planet, Long> {
    void deletePlanetById(Long id);
}
