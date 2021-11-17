package ch.zli.m223.punchclock.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
public class Privilege {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean canEdit;

    @Column(nullable = false)
    private Boolean seeUsers;

    @Column(nullable = false)
    private Boolean crudUsers;

    @OneToMany(mappedBy = "privilege", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;

}
