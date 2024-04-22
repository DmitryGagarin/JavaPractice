package registration.example.reg.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "beer_names")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long rating;
    private Long reviews;
    private Long price;

}
