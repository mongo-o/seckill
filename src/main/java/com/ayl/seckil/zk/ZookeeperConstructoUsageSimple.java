package com.ayl.seckil.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author AYL    2018/8/28 13:53
 */
public class ZookeeperConstructoUsageSimple implements Watcher {
    private static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException {
        String connectionStr = "192.168.11.88:2181,192.168.11.89:2181,192.168.11.90:2181";
        ZooKeeper zooKeeper = new ZooKeeper(connectionStr, 5000, new ZookeeperConstructoUsageSimple());
        System.out.println("========zk state:" + zooKeeper.getState());

        try {
            connectedSemaphore.await();

            String path1 = zooKeeper.create("/node_create", "create".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {

        }

        System.out.println("zookeeper session established.");
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("received watched event:"  + watchedEvent);

        if(Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
            System.out.println("processed ======");
        }
    }
}
