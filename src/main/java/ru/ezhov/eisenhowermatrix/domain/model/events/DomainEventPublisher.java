package ru.ezhov.eisenhowermatrix.domain.model.events;

import java.util.*;

public class DomainEventPublisher {
    private static DomainEventPublisher domainEventPublisher;

    private Map<Class, List> subscriberMap = new HashMap<>();

    public static DomainEventPublisher instance() {
        if (domainEventPublisher == null) {
            domainEventPublisher = new DomainEventPublisher();
        }
        return domainEventPublisher;
    }

    public <T> void subscribe(DomainEventSubscriber<T> domainEventSubscriber) {
        List list = subscriberMap.get(domainEventSubscriber.subscribedToEventType());
        if (list == null) {
            list = new ArrayList();
            subscriberMap.put(domainEventSubscriber.subscribedToEventType(), list);
        }
        list.add(domainEventSubscriber);
    }

    public <T> void publish(final T domainEvent) {
        List<DomainEventSubscriber<T>> domainEventSubscribers = subscriberMap.get(domainEvent.getClass());
        if (domainEventSubscribers != null) {
            for (DomainEventSubscriber domainEventSubscriber : domainEventSubscribers) {
                domainEventSubscriber.handleEvent(domainEvent);
            }
        }
    }
}
