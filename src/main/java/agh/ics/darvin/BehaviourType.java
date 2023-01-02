package agh.ics.darvin;

import java.util.Random;

public enum BehaviourType {
    FULL_PREDESTINATION,
    SOME_MADNESS;
    private static final Random random = new Random();
    public int getNextIndex(int i, int n){
        return switch (this){
            case FULL_PREDESTINATION -> (i + 1) % n;
            case SOME_MADNESS -> getSomeMadness(i, n);
        };
    }


    private int getSomeMadness(int i, int n){
        if(random.nextInt() % 100 < 80){
            return (i+1) % n;
        }
        else return (Math.abs(random.nextInt()) % n);
    }
}
