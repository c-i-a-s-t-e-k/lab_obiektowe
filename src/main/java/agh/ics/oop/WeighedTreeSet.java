package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
// klasa ma za zadanie przechowywać duplikaty danych w treeSet
public class WeighedTreeSet {
    private final TreeSet<Integer> set;
    private final Map<Integer, Integer> map;
    
    public WeighedTreeSet(){
        set = new TreeSet<>();
        map = new HashMap<>();
    }

    public void add(Integer x){
        if (! set.contains(x)){
            set.add(x);
            map.put(x,1);
        }
        else {
            map.put(x, map.remove(x) + 1);
        }
    }
    public boolean remove(Integer x){
        if (set.contains(x)){
            if(map.get(x) > 1){
                map.put(x,map.remove(x) - 1);
                return true;
            } else if (map.get(x) == 1) {
                map.remove(x);
                set.remove(x);
                return true;
            }else {
                return false;
            }
        }
        else return false;
    }
    public Integer last(){
        return set.last();
    }
    public Integer first(){
        return set.first();
    }
    public boolean isEmpty(){
        return set.isEmpty();
    }
}
