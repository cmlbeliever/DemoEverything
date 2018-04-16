package com.cml.framework.actor.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class DeviceSenderActor extends AbstractActor {

	public static Props props() {
		return Props.create(DeviceSenderActor.class);
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(DeviceBean.class, d -> {
			System.out.println("===DeviceSenderActor received Message===>" + d.getName());
		}).build();
	}

}
