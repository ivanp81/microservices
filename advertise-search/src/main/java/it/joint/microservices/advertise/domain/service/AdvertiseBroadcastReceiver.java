package it.joint.microservices.advertise.domain.service;

public interface AdvertiseBroadcastReceiver {

	public void onAdvertiseCreatedMessage(String advertiseSaved);
    public void onAdvertiseDeletedMessage(String advertiseDeleted);
}
