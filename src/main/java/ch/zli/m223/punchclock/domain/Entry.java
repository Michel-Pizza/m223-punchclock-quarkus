package ch.zli.m223.punchclock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isLogin;

    @ManyToOne
    @JoinColumn (name = "workplace_id", nullable = false)
    private Workplace workplace;


    @ManyToOne
    @JoinColumn (name = "user_id", nullable = false)
    private User user;



}