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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list all entries from user", description = "Gets the Username from the JWT-token and asks if the Database has any entries of him. If true, the database sends every entry from the user back as response")
    @Path("/user")
    public List<Entry> listFromUser(@Context SecurityContext ctx) {
        return entryService.findAllFromUser(ctx.getUserPrincipal().getName());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list one Entry", description = "gets a id from the entry and sends it back")
    @Path("/{id}")
    public Entry getSingleEntry(@PathParam Long id) {
        return entryService.getSingleEntry(id);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(@Context SecurityContext ctx, Entry entry) {
        return entryService.createEntry(entry, ctx.getUserPrincipal().getName());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    @Operation(summary = "Adds a modified entry for, when a user logs in", description = "Creates a modified Entry, if the user successfully logs in")
    public Entry addLoginEntry(Entry entry) {return entryService.createLoginEntry(entry);}

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit an Entry", description = "Edits an entry based on id and New Changes")
    @Path("/")
    public Entry edit(Entry entry) {return entryService.changeEntry(entry);}


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit basic stuff for an Entry", description = "Edits stuff for changing workplace and description. times cannot be changed with this")
    @Path("/basic")
    public Entry editBasic(Entry entry) {return entryService.changeBasicEntry(entry);}


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Finishes an Entry", description = "When a user logs out, it will finish the login-entry by setting the checkout Time")
    @Path("/finish")
    public Entry finish(Entry entry) {return entryService.finishEntry(entry);}


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)

    @Operation(summary = "Delete an Entry", description = "Deletes an entry based on id.")
    @Path("/{id}")
    public String delete(@PathParam Long id) { return entryService.deleteEntry(id);}





}
