package com.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * 
 * The class contains  a method that tells  us whether a node exists or not  (if it exists , it will print the node details ) 
 * 
 *
 *
 */
public class ZookeeperNodeExists implements Watcher {

	private ZooKeeper zookeeperClient = null;
	
	public ZookeeperNodeExists() {
		try {
			zookeeperClient = new ZooKeeper(ZookeeperConstants.ZOOKEEPER_URL, 30000, this);
			System.out.println(zookeeperClient.getSessionId());
			System.out.println(zookeeperClient.getState().toString());
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
	
	
	private void doesZNodeExists(String key)  {
		 try {
			 Stat stat =  zookeeperClient.exists(key, this);
			 if(stat == null) {
				 System.out.println(" the requested key is not there with the zookeeper ");
			 } else {
				 System.out.println("version of the node " + stat.getVersion());
				 System.out.println("cversion of the node " + stat.getCversion());
				 System.out.println("aversion of the node " + stat.getAversion());
				 System.out.println(" number of children of the node " + stat.getNumChildren());
				 System.out.println(" dataLength  of the node " + stat.getDataLength());
			 }
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		ZookeeperNodeExists zookeeperNodeExists = new ZookeeperNodeExists();
		String key = ZookeeperConstants.ZOOKEEPER_PERSISTENT_NODE_KEY;
		zookeeperNodeExists.doesZNodeExists(key);
		
		try {
			Thread.currentThread().join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
