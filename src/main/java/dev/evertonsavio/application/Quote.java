package dev.evertonsavio.application;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Quote {

    public String id;
    public int price;

    public Quote() {

    }

    public Quote(String id, int price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{\"id\": \"" + id + "\", \"price\": " + price + "}";
    }

}
