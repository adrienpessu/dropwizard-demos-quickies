package com.github.adrienpessu.quickies.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import javax.validation.constraints.NotNull;

@Entity("quickie")
public class Quickie {

    @Id
    private ObjectId id;
    @NotNull
    private String name;
    private long duration;

    public Quickie() {
    }

    @JsonCreator
    public Quickie(@JsonProperty("id") final ObjectId id,
        @JsonProperty("name")  final String name,
        @JsonProperty("duration")  final long duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    @JsonProperty("id")
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }

}