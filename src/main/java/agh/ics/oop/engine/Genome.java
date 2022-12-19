package agh.ics.oop.engine;

import java.util.Random;

public class Genome {
    static private int genomeLength;
    static private int mutationNumber;
    private int actualGeneIndex;
    private final int[] genes;

    Genome(int[] genes){
        this.genes = genes;
        this.actualGeneIndex = 0;
    }

    public int getGene(){
        int actualGene = genes[actualGeneIndex];
        actualGeneIndex++;
        return actualGene;
    }

    static public void genomeInitialization(int genomeLength, int mutationNumber){
        if (genomeLength > 0)
            Genome.genomeLength = genomeLength;
        else
            throw new IllegalArgumentException("genome length must be higher than 0");
        if (mutationNumber > 0)
            Genome.mutationNumber = mutationNumber;
        else
            throw new IllegalArgumentException("number of mutation must be 0 or higher");
    }
    static private int genesFromShare(int share1, int share2){
        zfhd
        return 0;
    }
    static private void mutation(Genome genome){
        fdh
    }

    static public Genome genomeCreation(Genome genome1, int share1, Genome genome2, int share2){
        Genome strongerGenome = null;
        Genome weakerGenome = null;
        if (share1 < share2){
            strongerGenome = genome2;
            weakerGenome = genome1;
        }
        else {
            strongerGenome = genome1;
            weakerGenome = genome2;
        }
        int strongerGenesNumber = genesFromShare(share1, share2);
        Side side = Side.randomSide();
        int[] genes = new int[Genome.genomeLength];

        if(side == Side.Left){
            for (int i = 0; i < strongerGenesNumber; i++){
                genes[i] = strongerGenome.genes[i];
            }
            for (int i = strongerGenesNumber; i < Genome.genomeLength; i++){
                genes[i] = weakerGenome.genes[i];
            }
        }else {
            for (int i = 0; i < Genome.genomeLength - strongerGenesNumber; i++){
                genes[i] = strongerGenome.genes[i];
            }
            for (int i = Genome.genomeLength - strongerGenesNumber; i < Genome.genomeLength; i++){
                genes[i] = weakerGenome.genes[i];
            }
        }

        Genome newGenome = new Genome(genes);
        mutation(newGenome);
        return newGenome;
    }
}
enum Side{
    Left,
    Right;

    static public Side randomSide(){
        if ((int)(Math.random() + 0.5) == 0) return Left;
        else return Right;
    }
}