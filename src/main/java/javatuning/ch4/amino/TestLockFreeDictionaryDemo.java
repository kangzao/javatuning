package javatuning.ch4.amino;

import org.amino.ds.lockfree.LockFreeDictionary;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class TestLockFreeDictionaryDemo {

    @Test
    public void test() {
        LockFreeDictionary<Integer, Object> map = new LockFreeDictionary<Integer, Object>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        Set<Integer> keys = map.keySet();
        for (Integer i : keys) {
            System.out.println(i);
        }
    }

    @Test
    public void testTreeMap() {
        TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        Set<Integer> keys = map.keySet();
        for (Integer i : keys) {
            System.out.println(i);
        }
    }

    @Test
    public void testHashMap() {
        HashMap<Integer, Object> map = new HashMap<Integer, Object>();
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
        }
        Set<Integer> keys = map.keySet();
        for (Integer i : keys) {
            System.out.println(i);
        }
    }

}
