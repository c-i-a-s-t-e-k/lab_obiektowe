package agh.ics.darvin;

import agh.ics.darvin.enums.BehaviourType;
import agh.ics.darvin.enums.MutationType;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Genome {
    static private int genomeLength;
    static private int minMutationNumber;
    static private int maxMutationNumber;
    static private MutationType mutationType;
    static private BehaviourType behaviourType;
    static private final Random random = new Random();
    private int actualGeneIndex;
    private final int[] genes;

    Genome(int[] genes){
        this.genes = genes;
        this.actualGeneIndex = 0;
    }
    Genome(){
        Random random = new Random();
        int[] genes = new int[Genome.genomeLength];
        for(int i = 0; i < Genome.genomeLength; i++){
            genes[i] = Math.abs(random.nextInt()) % 8;
        }
        this.genes = genes;
        this.actualGeneIndex = Math.abs(random.nextInt()) % Genome.genomeLength;
    }

    public int getGene(){
        int actualGene = genes[actualGeneIndex];
        this.actualGeneIndex = behaviourType.getNextIndex(this.actualGeneIndex, Genome.genomeLength);
        return actualGene;
    }
    static public void initGenome(int genomeLength, int maxMutationNumber, MutationType mutationType, BehaviourType behaviourType){
        Genome.initGenome(genomeLength, 0, maxMutationNumber, mutationType, behaviourType);
    }

    static public void initGenome(int genomeLength,int minMutationNumber, int maxMutationNumber, MutationType mutationType, BehaviourType behaviourType){
        Genome.mutationType = mutationType;
        Genome.behaviourType = behaviourType;
        if (maxMutationNumber < minMutationNumber)
            throw new IllegalArgumentException("minimal mutation number must be equal or lower than maximal mutation number");
        if (genomeLength > 0)
            Genome.genomeLength = genomeLength;
        else
            throw new IllegalArgumentException("genome length must be higher than 0");
        if (minMutationNumber >= 0){
            Genome.minMutationNumber = minMutationNumber;
            Genome.maxMutationNumber = maxMutationNumber;
        }
        else throw new IllegalArgumentException("number of mutation must be 0 or higher");
    }

    static private int getMutationNumber(){
        if(Genome.minMutationNumber == Genome.maxMutationNumber) return 0;
        else return (random.nextInt() % (Genome.maxMutationNumber - Genome.minMutationNumber)) + Genome.minMutationNumber;
    }
    static private int genesFromShare(int share1, int share2){
        int tmp = Math.min(share1, share2);
        share1 = Math.max(share1, share2);
        share2 = tmp;
        return (int)((float)share1/(share1 + share2)*Genome.genomeLength);
    }
    static private void mutation(Genome genome){
        Random random = new Random();
        Set<Integer> indexes = new HashSet<>();
        int i = 0;
        while (i < Genome.getMutationNumber()){
            Integer newIndex = random.nextInt();
            if (! indexes.contains(newIndex)) {
                indexes.add(newIndex);
                i++;
            }
        }
        for (Integer index :  indexes){
            genome.genes[index] = Genome.mutationType.mutateGene(genome.genes[index]);
        }
    }

    static public Genome genomeCreation(Genome genome1, int share1, Genome genome2, int share2){
        if (share1 < share2){
            Genome tmp = genome1;
            genome1 = genome2;
            genome2 = tmp;
        }

        int strongerGenesNumber = genesFromShare(share1, share2);
        Side side = Side.randomSide();
        int[] genes = new int[Genome.genomeLength];

        if(side == Side.Left){
            for (int i = 0; i < strongerGenesNumber; i++){
                genes[i] = genome1.genes[i];
            }
            for (int i = strongerGenesNumber; i < Genome.genomeLength; i++){
                genes[i] = genome2.genes[i];
            }
        }else {
            for (int i = 0; i < Genome.genomeLength - strongerGenesNumber; i++){
                genes[i] = genome2.genes[i];
            }
            for (int i = Genome.genomeLength - strongerGenesNumber; i < Genome.genomeLength; i++){
                genes[i] = genome1.genes[i];
            }
        }

        Genome newGenome = new Genome(genes);
        mutation(newGenome);
        return newGenome;
    }
}

