package agh.ics.darvin;

import agh.ics.darvin.config.GenomeConfig;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Genome {
    private final GenomeConfig config;
    static private final Random random = new Random();
    private int actualGeneIndex;
    private final int[] genes;

    Genome(int[] genes, GenomeConfig config) {
        this.genes = genes;
        this.actualGeneIndex = 0;
        this.config = config;
    }

    Genome(GenomeConfig config) {
        this.config = config;
        Random random = new Random();
        int[] genes = new int[config.get_genomeLength()];
        for (int i = 0; i < config.get_genomeLength(); i++) {
            genes[i] = Math.abs(random.nextInt()) % 8;
        }
        this.genes = genes;
        this.actualGeneIndex = Math.abs(random.nextInt()) % config.get_genomeLength();
    }

    public int getGene() {
        int actualGene = genes[actualGeneIndex];
        this.actualGeneIndex = config.get_behaviourType().getNextIndex(this.actualGeneIndex, config.get_genomeLength());
        return actualGene;
    }
//    static public void initGenome(int genomeLength, int maxMutationNumber, MutationType mutationType, BehaviourType behaviourType){
//        Genome.initGenome(genomeLength, 0, maxMutationNumber, mutationType, behaviourType);
//    }

//    static public void initGenome(int genomeLength,int minMutationNumber, int maxMutationNumber, MutationType mutationType, BehaviourType behaviourType){
//        Genome.mutationType = mutationType;
//        Genome.behaviourType = behaviourType;
//        if (maxMutationNumber < minMutationNumber)
//            throw new IllegalArgumentException("minimal mutation number must be equal or lower than maximal mutation number");
//        if (genomeLength > 0)
//            Genome.genomeLength = genomeLength;
//        else
//            throw new IllegalArgumentException("genome length must be higher than 0");
//        if (minMutationNumber >= 0){
//            Genome.minMutationNumber = minMutationNumber;
//            Genome.maxMutationNumber = maxMutationNumber;
//        }
//        else throw new IllegalArgumentException("number of mutation must be 0 or higher");
//    }

    private int getMutationNumber() {
        if (config.get_minMutationNumber() == config.get_maxMutationNumber()) return 0;
        else
            return (random.nextInt() % (config.get_maxMutationNumber() - config.get_minMutationNumber())) + config.get_minMutationNumber();
    }

    private int genes_from_left_share(int left, int right) {
//        int tmp = Math.min(share1, share2);
//        share1 = Math.max(share1, share2);
//        share2 = tmp;
        return (int) ((float) left / (left + right) * config.get_genomeLength());
    }

    private void mutation(Genome genome) {
        Random random = new Random();
        Set<Integer> indexes = new HashSet<>();
        int i = 0;
        while (i < this.getMutationNumber()) {
            Integer newIndex = random.nextInt();
            if (!indexes.contains(newIndex)) {
                indexes.add(newIndex);
                i++;
            }
        }
        for (Integer index : indexes) {
            genome.genes[index] = config.get_mutationType().mutateGene(genome.genes[index]);
        }
    }

    public Genome mergeWith(Genome other, int this_energy, int other_energy) {
//        if (this_energy < other_energy){
//            Genome tmp = other;
//            other = genome2;
//            genome2 = tmp;
//        }
        int thisGenesNumber = genes_from_left_share(this_energy, other_energy);
        int otherGenesNumber = this.genes.length - thisGenesNumber;
        Side side = Side.randomSide();
        int[] genes = new int[config.get_genomeLength()];

        if (side == Side.Left) {
            for (int i = 0; i < genes.length; i++)
                genes[i] = i <= thisGenesNumber ? this.genes[i] : other.genes[i];
        } else {
            for (int i = 0; i < genes.length; i++)
                genes[i] = i <= otherGenesNumber ? this.genes[i] : other.genes[i];
        }

        Genome newGenome = new Genome(genes, config);
        mutation(newGenome);
        return newGenome;
    }
}

