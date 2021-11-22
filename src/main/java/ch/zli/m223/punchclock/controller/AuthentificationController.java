package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Date;

@Log
@Log4j2
@Slf4j
@Path ("/auth")
public class AuthentificationController {

    @Inject
    AuthenticationService authenticationService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(User user){

        Date d = new Date();
        d.setTime(System.currentTimeMillis() +10000000);
        if(authenticationService.checkIfUserExists(user)){
            return authenticationService.generateValidJwtToken(user);
        }else {
            throw new NotAuthorizedException("User " + user.getUsername() + " was not found");
        }
    }
}
