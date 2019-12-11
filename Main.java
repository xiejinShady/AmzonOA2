package OA2;

import java.util.*;

public class Main {

    public static void main(String[] args) {

       List<Integer> test = new ArrayList<>();
       for (int i = 1; i < 10; i++) {
           test.add(i);
       }

       int value = test.iterator().next();
       int second = test.iterator().next();

       System.out.println(value);
       System.out.println(second);

    }
    class LFUCache {

        HashMap<Integer,Integer> counts;
        HashMap<Integer,Integer> vals;
        //key is used time,value is a list contains all cached value that is used that time
        HashMap<Integer,LinkedHashSet<Integer>> lists;
        int cap;
        int min = -1;

        public LFUCache(int capacity) {
            this.cap = capacity;
            vals = new HashMap<>();
            lists = new HashMap<>();
            counts = new HashMap<>();
            lists.put(1,new LinkedHashSet<>());
        }

        public int get(int key) {

            if (!vals.containsKey(key)) {
                return -1;
            }
            int count = counts.get(key);
            counts.put(key,count + 1);
            lists.get(count).remove(key);
            if (count == min && lists.get(count).size() == 0) {
                min++;
            }
            if (!lists.containsKey(count + 1)) {
                lists.put(count,new LinkedHashSet<>());
            }
            lists.get(count + 1).add(key);

            return vals.get(key);

        }

        public void put(int key, int value) {

            if(cap < 0) {
                return;
            }

            if(vals.containsKey(key)) {
                vals.put(key,value);
                get(key);
                return;
            }

            if(vals.size() >= cap) {
                int evit = lists.get(min).iterator().next();
                lists.get(min).remove(evit);
                vals.remove(evit);
                counts.remove(evit);
            }

            vals.put(key,value);
            counts.put(key,1);
            min = 1;
            lists.get(1).add(key);
        }
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}