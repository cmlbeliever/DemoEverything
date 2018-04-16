package com.cml.framework.actor.akka.actor;

import java.util.Optional;

import akka.actor.AbstractActor;
import akka.actor.Props;
import scala.Option;

public class HelloWorldActor extends AbstractActor {

	public static Props props() {
		return Props.create(HelloWorldActor.class);
	}

	private static void log(String log) {
		System.out.println("HelloWorldActor=============>" + log + "<==================");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		super.preRestart(reason, message);
		log("preRestart");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		super.postRestart(reason);
		log("postRestart");
	}

	@Override
	public void postStop() throws Exception {
		super.postStop();
		log("postStop");
	}

	@Override
	public void aroundPostRestart(Throwable reason) {
		super.aroundPostRestart(reason);
		log("aroundPostRestart");
	}

	@Override
	public void aroundPostStop() {
		super.aroundPostStop();
		log("aroundPostStop");
	}

	@Override
	public void aroundPreRestart(Throwable reason, Option<Object> message) {
		super.aroundPreRestart(reason, message);
		log("aroundPreRestart");
	}

	@Override
	public void aroundPreStart() {
		log("aroundPreRestart before");
		super.aroundPreStart();
		log("aroundPreRestart after");
	}

	@Override
	public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
		super.preRestart(reason, message);
		log("preRestart");
	}

	@Override
	public void preStart() throws Exception {
		super.preStart();
		log("preStart");
	}

	@Override
	public void unhandled(Object message) {
		super.unhandled(message);
		log("unhandled");
	}

	@Override
	public Receive createReceive() {
		log("createReceive");
		return receiveBuilder().match(String.class, r -> {
			System.out.println("HelloWorldActor received message===>" + r);
			DeviceBean device = new DeviceBean();
			device.setName("modified by helloworld actor :" + r);
			getSender().tell(device, getSelf());
		}).build();
	}

}
