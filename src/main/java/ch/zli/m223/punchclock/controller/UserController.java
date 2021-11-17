package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path ("/users")
@Tag (name = "users", description = "Handling of users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "List all users")
    public List<User> list() {
        return userService.findAll();
    }


    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "list one User")
    @Path ("/{id}")
    public User getSingleUser(@PathParam Long id) {
        return userService.getSingleUser(id);
    }

    @POST
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation (summary = "Add a new User", description = "The newly created user is returned. The id may not be passed.")
    public User add(User user) {
        return userService.createUser(user);
    }


    @PUT
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation (summary = "Edit an User", description = "Edits an user based on id and New Changes")
    @Path ("/")
    public User edit(User user) {
        return userService.changeUser(user);
    }


    @DELETE
    @Produces (MediaType.APPLICATION_JSON)

    @Operation (summary = "Delete an User", description = "Deletes an user based on id.")
    @Path ("/{id}")
    public String delete(@PathParam Long id) {
        return userService.deleteUser(id);
    }


}