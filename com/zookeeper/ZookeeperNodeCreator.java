package com.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * 
 * The class contains a method that creates a zookeeper node 
 * 
 *
 */
public class ZookeeperNodeCreator implements Watcher {

	private ZooKeeper zookeeperClient = null;
	
	
	public ZookeeperNodeCreator() {
		try {
			zookeeperClient = new ZooKeeper(ZookeeperConstants.ZOOKEEPER_URL, 30000, this);
		} catch (Exception e) {
			System.out.println("Exception while initializing  the zookeeper client, the reason is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void process(WatchedEvent watchedEvent) {
		System.out.println(" watched event " + watchedEvent);
		System.out.println(" Event Path :: " + watchedEvent.getPath());
		System.out.println(" Event Type " + watchedEvent.getState().name());
	}
	
	
	private void createPersistentZookeeperNode(String key, String value) {			
		try {
			String pathOfTheNode = zookeeperClient.create(key, value.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println(" The  node is created, the path is " + pathOfTheNode);			
		} catch (Exception e) {
			System.out.println(" there is an exception while creating a persistent node. the reason is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void createEphemeralZookeeperNode(String key, String value) {					
		try {
			zookeeperClient.create(key, value.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ZookeeperNodeCreator zookeeperNodeCreator = new ZookeeperNodeCreator();
		String key = ZookeeperConstants.ZOOKEEPER_PERSISTENT_NODE_KEY;
		String value = ZookeeperConstants.ZOOKEEPER_PERSISTENT_NODE_VALUE;
		zookeeperNodeCreator.createPersistentZookeeperNode(key, value);
		
		/*zookeeperNodeCreator.createPersistentZookeeperNode(ZookeeperConstants.ZOOKEEPER_CHILD_NODE_PARENT, value);
		zookeeperNodeCreator.createPersistentZookeeperNode(ZookeeperConstants.ZOOKEEPER_CHILD_NODE_1, value);
		zookeeperNodeCreator.createPersistentZookeeperNode(ZookeeperConstants.ZOOKEEPER_CHILD_NODE_2, value);*/
	}
}
