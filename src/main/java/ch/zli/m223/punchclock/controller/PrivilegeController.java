package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.Privilege;
import ch.zli.m223.punchclock.service.PrivilegeService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path ("/privileges")
@Tag (name = "privileges", description = "Handling of privileges")
public class PrivilegeController {

    @Inject
    PrivilegeService privilegeService;


    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "List all privileges")
    public List<Privilege> list() {
        return privilegeService.findAll();
    }


    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "list one Privilege")
    @Path ("/{id}")
    public Privilege getSinglePrivilege(@PathParam Long id) {
        return privilegeService.getSinglePrivilege(id);
    }

    @POST
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation (summary = "Add a new Privilege", description = "The newly created privilege is returned. The id may not be passed.")
    public Privilege add(Privilege privilege) {
        return privilegeService.createPrivilege(privilege);
    }


    @PUT
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation (summary = "Edit an Privilege", description = "Edits an privilege based on id and New Changes")
    @Path ("/")
    public Privilege edit(Privilege privilege) {
        return privilegeService.changePrivilege(privilege);
    }


    @DELETE
    @Produces (MediaType.APPLICATION_JSON)

    @Operation (summary = "Delete an Privilege", description = "Deletes an privilege based on id.")
    @Path ("/{id}")
    public String delete(@PathParam Long id) {
        return privilegeService.deletePrivilege(id);
    }


}