package javatuning.ch2.pool;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import java.util.concurrent.atomic.AtomicInteger;

public class ObjectPoolDemo {

    static PoolableObjectFactory factory = new PoolableObjectFactoryDemo();
    static ObjectPool pool = new GenericObjectPool(factory);
    private static AtomicInteger endcount = new AtomicInteger(0);

    public static class PoolThread extends Thread {
        public void run() {
            Object obj = null;
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("== " + i + " ==");
                    obj = pool.borrowObject();
                    System.out.println(obj + " is get");
                    pool.returnObject(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                endcount.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) {
        new PoolThread().start();
        new PoolThread().start();
        new PoolThread().start();
        try {
            while (true) {
                if (endcount.get() == 3) {
                    pool.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
