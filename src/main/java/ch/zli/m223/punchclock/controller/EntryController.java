package ch.zli.m223.punchclock.controller;


import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;



import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
@RolesAllowed({"user", "vorgesetzter", "admin"})
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries")
    @Path("")
    public List<Entry> list() {
        return entryService.findAll();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list one Entry")
    @Path("/{id}")
    public Entry getSingleEntry(@PathParam Long id) {
        return entryService.getSingleEntry(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list all entries from user")
    @Path("/user")
    public List<Entry> listFromUser(@Context SecurityContext ctx) {
        return entryService.findAllFromUser(ctx.getUserPrincipal().getName());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
        return entryService.createEntry(entry, "Michel");
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit an Entry", description = "Edits an entry based on id and New Changes")
    @Path("/")
    public Entry edit(Entry entry) {return entryService.changeEntry(entry);}


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)

    @Operation(summary = "Delete an Entry", description = "Deletes an entry based on id.")
    @Path("/{id}")
    public String delete(@PathParam Long id) { return entryService.deleteEntry(id);}



}
