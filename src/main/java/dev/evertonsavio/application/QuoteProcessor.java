package dev.evertonsavio.application;

import java.util.Random;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;

import com.google.gson.Gson;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
public class QuoteProcessor {

    private Random random = new Random();

    @Blocking
    @Incoming("requests")
    //@Outgoing("quotes")
    public CompletionStage<Void> process(Message<String> message) {
        System.out.println("RABBITMQ MESSAGE: " + message.getPayload());
        Gson gson = new Gson();
        Quote payload = gson.fromJson(message.getPayload(), Quote.class);
        System.out.println(payload);
        //return new Quote(quoteRequest, random.nextInt(100));
        return message.ack();
    }

//    @Blocking
//    @Incoming("final-quotes")
//    public void processFinal(String message)
//    {
//        System.out.println(message);
//        //return message.ack();
//    }

}