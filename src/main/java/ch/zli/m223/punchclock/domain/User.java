package ch.zli.m223.punchclock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private Boolean present;

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private Privilege privilege;


    @OneToMany (mappedBy = "user", orphanRemoval = true)
    private List<Entry> entries;

}
