package javatuning.ch3.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TestTreeMap {

    private Map map;

    public static class Student implements Comparable<Student> {
        private String name;
        private int score;

        public Student(String name, int s) {
            this.name = name;
            this.score = s;
        }

        @Override
        public int compareTo(Student o) {
            if (o.score < this.score)
                return 1;
            else if (o.score > this.score)
                return -1;
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("name=").append(name).append(",").append("score=").append(score);
            return sb.toString();
        }
    }

    public static class StudentDetailInfo {
        Student s;

        public StudentDetailInfo(Student s) {
            this.s = s;
        }

        @Override
        public String toString() {
            return s.name + "'s detail information";
        }
    }

    public void initMap() {
        map = new TreeMap();
        Student s1 = new Student("Billy", 70);
        Student s2 = new Student("David", 85);
        Student s3 = new Student("Kite", 92);
        Student s4 = new Student("Cissy", 68);
        map.put(s1, new StudentDetailInfo(s1));
        map.put(s3, new StudentDetailInfo(s3));
        map.put(s2, new StudentDetailInfo(s2));
        map.put(s4, new StudentDetailInfo(s4));
    }

    @Test
    public void testOrderKey() {
        initMap();
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            Student key = (Student) iterator.next();
            System.out.println(key + "->" + map.get(key));
        }
    }

    @Test
    public void testSubMap() {
        map = new TreeMap();
        Student s1 = new Student("Billy", 70);
        Student s2 = new Student("David", 85);
        Student s3 = new Student("Kite", 92);
        Student s4 = new Student("Cissy", 68);
        map.put(s1, new StudentDetailInfo(s1));
        map.put(s3, new StudentDetailInfo(s3));
        map.put(s2, new StudentDetailInfo(s2));
        map.put(s4, new StudentDetailInfo(s4));

        Map map1 = ((TreeMap) map).subMap(s4, s2);
        for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext(); ) {
            Student key = (Student) iterator.next();
            System.out.println(key + "->" + map1.get(key));
        }
        System.out.println("subMap end");

        map1 = ((TreeMap) map).headMap(s1);
        for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext(); ) {
            Student key = (Student) iterator.next();
            System.out.println(key + "->" + map1.get(key));
        }
        System.out.println("headMap end");

        map1 = ((TreeMap) map).tailMap(s1);
        for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext(); ) {
            Student key = (Student) iterator.next();
            System.out.println(key + "->" + map1.get(key));
        }
        System.out.println("tailMap end");
    }

}
