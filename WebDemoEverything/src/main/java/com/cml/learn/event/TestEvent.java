package com.cml.learn.event;

public class TestEvent {
	private int code;
	private String name;

	public TestEvent(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestEvent [code=" + code + ", name=" + name + "]";
	}

}
