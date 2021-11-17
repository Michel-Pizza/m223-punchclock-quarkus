package ch.zli.m223.punchclock.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String workplaceName;

    @OneToMany(mappedBy = "workplace", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Entry> entries;

}
