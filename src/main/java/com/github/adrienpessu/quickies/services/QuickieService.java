package com.github.adrienpessu.quickies.services;

import com.github.adrienpessu.quickies.configuration.QuickieConfiguration;
import com.github.adrienpessu.quickies.models.Quickie;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;
import java.util.Optional;

/**
 * Created by adrien on 23/11/16.
 */
public class QuickieService {
    private final Datastore datastore;

    private final QuickieConfiguration configuration;

    public QuickieService(QuickieConfiguration quickieConfiguration, Datastore datastore) {
        this.configuration = quickieConfiguration;
        this.datastore = datastore;
    }

    public Optional<Quickie> addQuickie(Quickie quickie) {
        this.datastore.save(quickie);
        return Optional.of(quickie);
    }

    public Optional<Quickie> updateQuickie(Quickie quickie) {

        this.datastore.save(quickie);
        return Optional.of(quickie);
    }

    public Optional<Quickie> deleteQuickie(Quickie quickie) {
        this.datastore.delete(quickie);
        return Optional.of(quickie);
    }

    public Optional<List<Quickie>> getQuickies() {
        this.datastore.createQuery(Quickie.class).asList();
        return Optional.of(this.datastore.createQuery(Quickie.class).asList());
    }
}
