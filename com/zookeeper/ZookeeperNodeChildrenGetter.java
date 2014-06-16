package com.zookeeper;

import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperNodeChildrenGetter implements Watcher {
	
	private ZooKeeper zookeeperClient = null;
	
	@Override
	public void process(WatchedEvent watchedEvent) {
		System.out.println(" watched event " + watchedEvent);
		System.out.println(" Event Path :: " + watchedEvent.getPath());
		System.out.println(" Event Type " + watchedEvent.getState().name());
	}
	
	public ZookeeperNodeChildrenGetter() {
		try {
			zookeeperClient = new ZooKeeper(ZookeeperConstants.ZOOKEEPER_URL, 30000, this);
		} catch (Exception e) {
			System.out.println("Exception while initializing  the zookeeper client, the reason is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void getZNodeChildren(String key)  {
		 try {
			 List<String> zookeeperChildren = zookeeperClient.getChildren(key, this); 
			 System.out.println(zookeeperChildren);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ZookeeperNodeChildrenGetter zookeeperNodeChildrenGetter = new ZookeeperNodeChildrenGetter();
		String key = ZookeeperConstants.ZOOKEEPER_CHILD_NODE_PARENT;
		zookeeperNodeChildrenGetter.getZNodeChildren(key);
	}
	


}
