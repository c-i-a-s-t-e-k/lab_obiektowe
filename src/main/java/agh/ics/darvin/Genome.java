package agh.ics.darvin;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Genome {
    static private int genomeLength;
    static private int mutationNumber;
    static private MutationType mutationType;
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
            genes[i] = random.nextInt() % 8;
        }
        this.genes = genes;
        this.actualGeneIndex = random.nextInt() % Genome.genomeLength;
    }

    public int getGene(){
        int actualGene = genes[actualGeneIndex];
        actualGeneIndex++;
        return actualGene;
    }

    static public void initGenome(int genomeLength, int mutationNumber, MutationType mutationType){
        Genome.mutationType = mutationType;
        if (genomeLength > 0)
            Genome.genomeLength = genomeLength;
        else
            throw new IllegalArgumentException("genome length must be higher than 0");
        if (mutationNumber >= 0)
            Genome.mutationNumber = mutationNumber;
        else
            throw new IllegalArgumentException("number of mutation must be 0 or higher");
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
        while (i < Genome.mutationNumber){
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

