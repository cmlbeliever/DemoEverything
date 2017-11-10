package com.cml.framework.zookeeper;

public class ZookeeperTest {
	public static void main(String[] args) throws Exception {
		ZookeeperManager zookeeperManager = new ZookeeperManager();
		zookeeperManager.connect("192.168.99.100:2181", 5000);
		zookeeperManager.createNode("/name", "modify Value!===!!");

		System.out.println("getNodeValue:" + zookeeperManager.getNodeValue("/name"));
		Thread.sleep(100000000);
	}
}
