package agh.ics.darvin.enums;

import java.util.Random;

public enum MutationType {
    FULL_RANDOM,
    SLIGHT_CHANGE,
    NO_CHANGE;

    private final Random random;

    private int fullRandomChange(int x){
        int result = this.random.nextInt() % 8;
        while (x == result)
            result = this.random.nextInt() % 8;
        return result;
    }
    private int slightChange(int x){
        int result = x;
        if (this.random.nextInt() % 2 == 0)
            result ++;
        else result --;

        if (result >= 0) return result % 8;
        else return 7;
    }
    private MutationType(){
        this.random = new Random();

    }

    public Integer mutateGene(int gene){
        return switch (this){
            case FULL_RANDOM -> fullRandomChange(gene);
            case SLIGHT_CHANGE -> slightChange(gene);
            case NO_CHANGE -> gene;
        };
    }
}
