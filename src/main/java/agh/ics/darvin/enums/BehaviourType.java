package agh.ics.darvin.enums;

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
    static public BehaviourType fromString(String name){
        for (var e : BehaviourType.values()) {
            if (name == e.toString())
                return e;
        }
        return BehaviourType.values()[0]; // On error return default value
    }


    private int getSomeMadness(int i, int n){
        if(random.nextInt() % 100 < 80){
            return (i+1) % n;
        }
        else return (Math.abs(random.nextInt()) % n);
    }
}
