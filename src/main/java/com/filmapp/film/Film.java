package com.filmapp.film;

import com.filmapp.category.Category;
import com.filmapp.person.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Person director;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "running_time")
    private Integer runningTime;

    @Column
    private Double budget;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "films_persons",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private Set<Person> actors;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
