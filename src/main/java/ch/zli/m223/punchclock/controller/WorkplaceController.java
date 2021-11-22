package ch.zli.m223.punchclock.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import ch.zli.m223.punchclock.domain.Workplace;
import ch.zli.m223.punchclock.service.WorkplaceService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path ("/workplaces")
@Tag (name = "workplaces", description = "Handling of workplaces")
public class WorkplaceController {

    @Inject
    WorkplaceService workplaceService;

    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "List all workplaces")
    public List<Workplace> list() {
        return workplaceService.findAll();
    }


    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "list one Workplace")
    @Path ("/{id}")
    public Workplace getSingleWorkplace(@PathParam Long id) {
        return workplaceService.getSingleWorkplace(id);
    }

    @POST
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation (summary = "Add a new Workplace", description = "The newly created workplace is returned. The id may not be passed.")
    public Workplace add(Workplace workplace) {
        return workplaceService.createWorkplace(workplace);
    }


    @PUT
    @Produces (MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation (summary = "Edit an Workplace", description = "Edits an workplace based on id and New Changes")
    @Path ("/")
    public Workplace edit(Workplace workplace) {
        return workplaceService.changeWorkplace(workplace);
    }


    @DELETE
    @Produces (MediaType.APPLICATION_JSON)

    @Operation (summary = "Delete an Workplace", description = "Deletes an workplace based on id.")
    @Path ("/{id}")
    public String delete(@PathParam Long id) {
        return workplaceService.deleteWorkplace(id);
    }


}