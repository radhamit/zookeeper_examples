package com.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperNodeDeletor implements Watcher {
	
	private ZooKeeper zookeeperClient = null;
	
	@Override
	public void process(WatchedEvent watchedEvent) {
		System.out.println(" watched event " + watchedEvent);
		System.out.println(" Event Path :: " + watchedEvent.getPath());
		System.out.println(" Event Type " + watchedEvent.getState().name());
	}
	
	
	public ZookeeperNodeDeletor() {
		try {
			zookeeperClient = new ZooKeeper(ZookeeperConstants.ZOOKEEPER_URL, 30000, this);
		} catch (Exception e) {
			System.out.println("Exception while initializing  the zookeeper client, the reason is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private void deleteZNode(String key)  {
		 try {
			 /** we should pass the version number, we should put -1 when  we don t need to worry about versions */
			 zookeeperClient.delete(key, 0);			 
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ZookeeperNodeDeletor zookeeperNodeCreator = new ZookeeperNodeDeletor();
		String key = ZookeeperConstants.ZOOKEEPER_PERSISTENT_NODE_KEY;
		zookeeperNodeCreator.deleteZNode(key);
	}
	
	
	

}
