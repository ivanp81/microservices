package it.joint.microservices.advertise.domain.event;

public enum AdvertiseEvent {

    SAVED("saved"), DELETED("deleted");

    private String name;

    private AdvertiseEvent(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }
}
