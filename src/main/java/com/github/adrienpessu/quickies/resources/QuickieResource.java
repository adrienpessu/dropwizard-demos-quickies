package com.github.adrienpessu.quickies.resources;

import com.github.adrienpessu.quickies.models.Quickie;
import com.github.adrienpessu.quickies.services.QuickieService;
import org.eclipse.jetty.http.HttpStatus;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrien on 23/11/16.
 */
@Path("/quickies")
@Produces(MediaType.APPLICATION_JSON)
public class QuickieResource {

    private QuickieService quickieService;

    public QuickieResource(QuickieService quickieService) {
        this.quickieService = quickieService;
    }

    @GET
    public Response getQuickies() {
        return this.quickieService.getQuickies()
            .map(quickies -> Response.status(Response.Status.OK).entity(quickies).build())
            .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @POST
    public Response createQuickies(@Valid Quickie quickie) {
        return this.quickieService.addQuickie(quickie)
            .map(pQuickie -> Response.status(Response.Status.CREATED).build())
            .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @PUT
    public Response updateQuickies(Quickie quickie) {
        return this.quickieService.updateQuickie(quickie)
            .map(pQuickie -> Response.status(Response.Status.OK).build())
            .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }

    @DELETE
    public Response deleteQuickies(Quickie quickie) {
        return this.quickieService.deleteQuickie(quickie)
            .map(pQuickie -> Response.status(Response.Status.OK).build())
            .orElse(Response.status(Response.Status.BAD_REQUEST).build());
    }
}
