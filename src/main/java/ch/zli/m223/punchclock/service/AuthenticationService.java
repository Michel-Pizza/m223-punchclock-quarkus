package ch.zli.m223.punchclock.service;

import java.time.Duration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


import ch.zli.m223.punchclock.domain.User;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

@RequestScoped

public class AuthenticationService {

    @Inject
    EntityManager entityManager;
    
    public String generateValidJwtToken(User user){

        var query = entityManager.createQuery("SELECT u.id FROM User u WHERE u.username = :name AND u.password = :password");

        query.setParameter("name", user.getUsername());
        query.setParameter("password", user.getPassword());

        var result = query.getSingleResult();

        User authUser = entityManager.find(User.class, result);

        String token =
            Jwt.issuer("https://zli.ch/issuer")
            .upn(user.getUsername())
            .groups(authUser.getPrivilege().getName())// hier dass hier einsetzen user.getPrivilege().getName();
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

    public boolean checkIfUserExists(User user) {

        var query = entityManager.createQuery(
                "SELECT COUNT(*) FROM User WHERE username = :name AND password = :password"
        );
        query.setParameter("name", user.getUsername());
        query.setParameter("password", user.getPassword());
        var result = query.getSingleResult();
        return (long)result == 1;
    }
}
