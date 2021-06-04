package NtiTeamTest.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "lord", fetch = FetchType.EAGER)
    private List<Planet> planet;

    public Lord() {
    }

    public List<Planet> getPlanet() {
        return planet;
    }

    public void setPlanet(List<Planet> planet) {
        this.planet = planet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
