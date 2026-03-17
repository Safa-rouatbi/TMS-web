package com.gpro.consulting.tiers.logistique.service.command.handler;

import com.gpro.consulting.tiers.logistique.service.exception.NotFoundException;

import java.util.Collection;
import java.util.function.Predicate;

public abstract class BaseChildCommandHandler {

    protected <T> T findRequired(Collection<T> collection, Predicate<T> predicate, String notFoundMessage) {
        if (collection == null) {
            throw new NotFoundException(notFoundMessage);
        }

        return collection.stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    protected <T> void removeRequired(Collection<T> collection, Predicate<T> predicate, String notFoundMessage) {
        T target = findRequired(collection, predicate, notFoundMessage);
        collection.remove(target);
    }
}
