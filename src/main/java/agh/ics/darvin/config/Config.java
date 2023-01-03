package agh.ics.darvin.config;

import agh.ics.darvin.InvalidConfigException;
import agh.ics.darvin.enums.BehaviourType;
import agh.ics.darvin.enums.BoundaryType;
import agh.ics.darvin.enums.ForestType;
import agh.ics.darvin.enums.MutationType;

public class Config implements AnimalConfig, GenomeConfig {
    public final int width;
    public final int height;
    public final ForestType forestType;
    public final BoundaryType boundaryType;
    public final int plantsNum;
    public final int energyFromPlant;
    public final int dailyGrowth;
    public final int animalNum;
    public final int startEnergy;
    public final int minEnergy;
    public final int energyCost;
    public final int minMutationNum;
    public final int maxMutationNum;
    public final MutationType mutationType;
    public final int genomeLength;
    public final BehaviourType behaviourType;

    public Config(int width, int height, ForestType forestType, BoundaryType boundaryType, int plantsNum, int energyFromPlant, int dailyGrowth, int animalNum, int startEnergy, int minEnergy, int energyCost, int minMutationNum, int maxMutationNum, MutationType mutationType, int genomeLength, BehaviourType behaviourType) throws InvalidConfigException {
        if (width < 2) throw new InvalidConfigException("Width is too small");
        this.width = width;
        if (height < 2) throw new InvalidConfigException("Height is too small");
        this.height = height;
        this.forestType = forestType;
        this.boundaryType = boundaryType;
        if (width * height < plantsNum) throw new InvalidConfigException("Too many plants for map area");
        this.plantsNum = plantsNum;
        if (energyFromPlant < 0) throw new InvalidConfigException("Energy from plant cannot be negative");
        this.energyFromPlant = energyFromPlant;
        this.dailyGrowth = dailyGrowth;
        this.animalNum = animalNum;
        if (startEnergy < 0) throw new InvalidConfigException("Starting energy cannot be negative");
        this.startEnergy = startEnergy;
        if (minEnergy < 0) throw new InvalidConfigException("Min energy cannot be negative");
        this.minEnergy = minEnergy;
        if (energyCost < 0) throw new InvalidConfigException("Energy cost cannot be negative");
        this.energyCost = energyCost;
        if (maxMutationNum < minMutationNum)
            throw new InvalidConfigException("Max mutations cannot be lower than min mutations");
        this.minMutationNum = minMutationNum;
        this.maxMutationNum = maxMutationNum;
        this.mutationType = mutationType;
        if (genomeLength < 1) throw new InvalidConfigException("Genome length cannot be negative");
        this.genomeLength = genomeLength;
        this.behaviourType = behaviourType;
    }

    @Override
    public int getStartEnergy() {
        return startEnergy;
    }

    @Override
    public int getEnergyFromPlant() {
        return energyFromPlant;
    }

    @Override
    public int getMinimalEnergyToReproduction() {
        return minEnergy;
    }

    @Override
    public int getReproductionCost() {
        return energyCost;
    }

    @Override
    public int get_genomeLength() {
        return genomeLength;
    }

    @Override
    public int get_minMutationNumber() {
        return minMutationNum;
    }

    @Override
    public int get_maxMutationNumber() {
        return maxMutationNum;
    }

    @Override
    public MutationType get_mutationType() {
        return mutationType;
    }

    @Override
    public BehaviourType get_behaviourType() {
        return behaviourType;
    }
}
