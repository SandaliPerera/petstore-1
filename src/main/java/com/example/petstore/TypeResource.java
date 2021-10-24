package com.example.petstore;

//import com.example.petstore.Type;
import com.example.petstore.db.DB;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/pettypes")
@Produces("application/json")
public class TypeResource {

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPetTypes() {
        return Response.ok(DB.getTypeTable()).build();
    }

    //--------------------------Search for a Pet Type---------------------------------
    @Path("{petTypeId}")
    @GET
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "PetType for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No PetType found for the id.") })
    public Response searchPetType(@PathParam("petTypeId") int id) {
        Type type = null;
        for (Type temp : DB.getTypeTable()) {
            if (temp.getPetTypeId() == id ){
                type=temp;
            }
        }
        return Response.ok(type).build();
    }

    //--------------------------Add New Pet Type---------------------------------
    @Path("addtype")
    @POST
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type added", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Pet Type adding failed.") })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPetType(@RequestBody Type type) {
        Type savedType = DB.savePetType(type);
        return Response.ok(savedType).build();
    }

    //--------------------------Update a Pet Type---------------------------------
    @Path("updatetype")
    @PUT
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Pet Type updating failed.") })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePetType(@RequestBody Type type) {
        Type updatedPetType = null;
        for (Type temp : DB.getTypeTable()) {
            if (temp.getPetTypeId().equals(type.getPetTypeId())){
                updatedPetType = DB.updateType(temp, type);
            }
        }
        return Response.ok(updatedPetType).build();
    }

    //--------------------------Delete a Pet Type---------------------------------
    @Path("deletetype")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePet(@RequestBody Type type) {
        for (Type temp : DB.getTypeTable()) {
            if (temp.getPetTypeId().equals(type.getPetTypeId())){
                DB.deleteType(temp);
            }
        }
    }

}
