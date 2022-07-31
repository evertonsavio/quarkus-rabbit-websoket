package dev.evertonsavio.application;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;
import java.util.UUID;

@Path("/hello")
public class QuoteController
{

    @Channel("quote-requests") Emitter<String> quoteRequestEmitter;
    private final Random random = new Random();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws InterruptedException
    {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public String createRequest() {
        UUID uuid = UUID.randomUUID();
        quoteRequestEmitter.send(new Quote(uuid.toString(), random.nextInt(100)).toString());
        return uuid.toString();
    }
}