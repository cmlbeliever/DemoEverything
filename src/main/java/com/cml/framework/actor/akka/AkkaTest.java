package com.cml.framework.actor.akka;

import com.cml.framework.actor.akka.actor.DeviceSenderActor;
import com.cml.framework.actor.akka.actor.HelloWorldActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaTest {
	public static void main(String[] args) throws Exception {
		ActorSystem system = ActorSystem.create("hello-world-system");
		try {
			ActorRef senderActor = system.actorOf(DeviceSenderActor.props());
			ActorRef supervisor = system.actorOf(HelloWorldActor.props(), "hello-supervisor");

			supervisor.tell("hello world", senderActor);
		} catch (Exception e) {
			e.printStackTrace();
		}


		system.terminate();
	}
}
