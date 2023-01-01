package agh.ics.darvin;

import java.util.Random;

enum MutationType {
    FULL_RANDOM,
    SLIGHT_CHANGE,
    NO_CHANGE;

    private final Random random;

    private int fullRandomChange(int x, int n){
        int result = this.random.nextInt() % n;
        while (x == result)
            result = this.random.nextInt() % n;
        return result;
    }
    private int slightChange(int x, int n){
        int result = x;
        if (this.random.nextInt() % 2 == 0)
            result ++;
        else result --;

        if (result >= 0) return result % n;
        else return n - 1;
    }
    private MutationType(){
        this.random = new Random();

    }

    public Integer mutateGene(int gene, int n){
        return switch (this){
            case FULL_RANDOM -> fullRandomChange(gene, n);
            case SLIGHT_CHANGE -> slightChange(gene, n);
            case NO_CHANGE -> gene;
        };
    }
}
