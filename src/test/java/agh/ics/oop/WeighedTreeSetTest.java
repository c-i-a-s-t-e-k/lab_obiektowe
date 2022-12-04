package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeighedTreeSetTest {
    @Test
    public void testRemove2SameX(){
        WeighedTreeSet set = new WeighedTreeSet();
        set.add(1);
        set.add(3);
        set.add(7);
        set.add(7);
        Assertions.assertTrue(set.remove(7));
        Assertions.assertEquals(7,set.last());
    }

    @Test
    public void testRemove(){
        WeighedTreeSet set = new WeighedTreeSet();
        set.add(1);
        set.add(3);
        set.add(7);
        Assertions.assertTrue(set.remove(7));
        Assertions.assertEquals(3,set.last());
    }

    @Test
    public void testAdd(){
        WeighedTreeSet set = new WeighedTreeSet();
        Assertions.assertTrue(set.isEmpty());
        set.add(3);
        Assertions.assertFalse(set.isEmpty());
        Assertions.assertEquals(3,set.first());
        set.add(3);
        Assertions.assertEquals(3,set.first());
    }
}
