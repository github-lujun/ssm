package com.jun;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * https://my.oschina.net/u/3796575/blog/1845035
 * */
public class App implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        zk = new ZooKeeper("localhost:2181", 5000, new App());
        connectedSemaphore.await();
        byte[] data = zk.getData("/username", true, stat);
        String s = new String(data);
        System.out.println(s);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            if(Event.EventType.None==watchedEvent.getType()&&watchedEvent.getPath()==null){
                connectedSemaphore.countDown();
            }else if(Event.EventType.NodeDataChanged ==watchedEvent.getType()){
                try {
                    System.out.println(new String(zk.getData(watchedEvent.getPath(),true,stat)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
