package agh.ics.darvin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMutationType {
    @Test
    public void slightChangeTest(){
        MutationType mutationType = MutationType.SLIGHT_CHANGE;
        Assertions.assertNotEquals(7, mutationType.mutateGene(7));
        Assertions.assertEquals(1, Math.abs(5 - mutationType.mutateGene(5)));
    }
    @Test
    public void fullrandomTest(){
        MutationType mutationType = MutationType.FULL_RANDOM;
        Assertions.assertNotEquals(7, mutationType.mutateGene(7));
    }
}
