package com.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperNodeValueGetter implements Watcher {
	
	private ZooKeeper zookeeperClient = null;
	
	public ZookeeperNodeValueGetter() {
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
	
	private void getZNodeValue(String key) {
		try {
			byte[] valueInByteArray =  zookeeperClient.getData(key, this, null);
			if(valueInByteArray == null) {
				System.out.println(" the value is null, looks like the key is not there ");
			} else {
		        String valueInString = new String(valueInByteArray);
		        System.out.println("the value is " + valueInString);
			}
		} catch (Exception e) {
			System.out.println(" There is  an exception while getting the znode value, the reason is " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ZookeeperNodeValueGetter zookeeperNodeCreator = new ZookeeperNodeValueGetter();
		String key = ZookeeperConstants.ZOOKEEPER_PERSISTENT_NODE_KEY;
		zookeeperNodeCreator.getZNodeValue(key);
	}
	
	
	
	

}
