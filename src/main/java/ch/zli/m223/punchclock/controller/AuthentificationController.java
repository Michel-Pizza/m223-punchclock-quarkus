package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path ("/auth")
public class AuthentificationController {

    @Inject
    AuthenticationService authenticationService;



    @Path("/loginn")
    public Response loginn(User user){
        if(authenticationService.checkIfUserExists(user)){
            return Response.ok("good").header("Set-Cookie", authenticationService.generateValidJwtToken(user.getUsername()) + ";SameSite=strict").build();
        }else {
            throw new NotAuthorizedException("User " + user.getUsername() + " was not found");
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces (MediaType.APPLICATION_JSON)
    public String login(User user){
        if(authenticationService.checkIfUserExists(user)){
            return authenticationService.generateValidJwtToken(user.getUsername());
        }else {
            throw new NotAuthorizedException("User " + user.getUsername() + " was not found");
        }
    }
}
