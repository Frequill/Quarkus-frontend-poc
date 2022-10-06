package def;

import def.proxy.TestProxy;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/main")
public class MainResource {

    @RestClient
    TestProxy proxy;

    @GET
    @Path("/testHello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response testHello(){
        String result = proxy.hello();
        System.out.println("TestHello went off!");
        return Response.ok(result).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginEntity loginEntity) {
        Response result = proxy.login(loginEntity);
        return Response.ok(result).build();
    }




}