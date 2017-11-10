package com.cml.framework.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZookeeperManager {
	private static final String ROOT_NODE = "/cml";
	private ZooKeeper zookeeper;

	public void connect(String address, int timeout) throws Exception {

		zookeeper = new ZooKeeper(address, timeout, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println("====event==>" + event);

				if (event.getState() == KeeperState.SyncConnected) {

					if (event.getType() == EventType.None) {
						System.out.println("connect zookeeper server success!!!");
						// 建立节点
						try {
							createRootNode();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (event.getType() == EventType.NodeDataChanged) {
						System.out.println("node data changed!!!" + event.getPath());
					}

				}
			}
		});

	}

	protected void createRootNode() throws KeeperException, Exception {
		Stat root = zookeeper.exists(ROOT_NODE, true);
		if (null == root) {
			System.out.println("rootNode not exist!!!");
			String rootNode = zookeeper.create(ROOT_NODE, "this is root Data".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("rootNode create success :" + rootNode);
		} else {
			System.out.println("root exist:");
		}
	}

	public void createNode(String node, String value) throws Exception {
		node = ROOT_NODE + node;
		Stat stat = zookeeper.exists(node, true);
		if (null == stat) {
			String childNode = zookeeper.create(node + node, value.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("childNode create result:" + childNode);
		} else {
			zookeeper.setData(node, value.getBytes(), stat.getVersion());
		}
	}

	public String getNodeValue(String node) throws Exception {
		node = ROOT_NODE + node;
		return new String(zookeeper.getData(node, true, new Stat()));
	}
}