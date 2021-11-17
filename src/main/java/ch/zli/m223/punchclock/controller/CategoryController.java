package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryController {

    @Inject
    CategoryService categoryService;


    @GET
    @Produces (MediaType.APPLICATION_JSON)
    @Operation (summary = "List all Entries")
    public List<Category> list() {
        return categoryService.findAll();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "list one category")
    @Path("/{id}")
    public Category getSingleCategory(@PathParam Long id) {
        return categoryService.getSingleCategory(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Category", description = "The newly created entry is returned. The id may not be passed.")
    public Category add(Category category) {
        return categoryService.createCategory(category);
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Edit a Category", description = "Edits an entry based on id and New Changes")
    @Path("/")
    public Category edit(Category category) {return categoryService.changeCategory(category);}


    @DELETE
    @Produces(MediaType.APPLICATION_JSON)

    @Operation(summary = "Delete a Category", description = "Deletes an entry based on id.")
    @Path("/{id}")
    public String delete(@PathParam Long id) { return categoryService.deleteCategory(id);}

}
