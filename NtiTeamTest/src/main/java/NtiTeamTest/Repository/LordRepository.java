package NtiTeamTest.Repository;

import NtiTeamTest.Entity.Lord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LordRepository extends JpaRepository<Lord, Long> {

    /**
     * - Найти всех Повелителей бездельников, которые прохлаждаются и не управляют никакими Планетами
     */
    @Query("select l from Lord l where l.planet.size = 0")
    List<Lord> findAllLordsWhoNotRuleAnyPlanets();

    /**
     * - Отобразить ТОП 10 самых молодых Повелителей
     */
    @Query("select l from Lord l order by l.age ASC")
    List<Lord> topTenYoungestLords(Pageable pageable);
}
