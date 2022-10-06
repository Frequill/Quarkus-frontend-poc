package def.proxy;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("myPath")
@Produces(MediaType.TEXT_PLAIN)
@RegisterRestClient(baseUri = "http://localhost:8082/")
public interface TestProxy {

    // FULL URL: http://localhost:8082/myPath/hello

    @GET
    @Path("/hello")
    String hello();
}
