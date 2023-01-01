package agh.ics.darvin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMutationType {
    @Test
    public void slightChangeTest(){
        MutationType mutationType = MutationType.SLIGHT_CHANGE;
        Assertions.assertNotEquals(7, mutationType.mutateGene(7, 10));
        Assertions.assertEquals(1, Math.abs(5 - mutationType.mutateGene(5, 7)));
    }
}
