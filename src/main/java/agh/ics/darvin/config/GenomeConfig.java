package agh.ics.darvin.config;

import agh.ics.darvin.enums.BehaviourType;
import agh.ics.darvin.enums.MutationType;

public interface GenomeConfig {
    int get_genomeLength();
    int get_minMutationNumber();
    int get_maxMutationNumber();
    MutationType get_mutationType();
    BehaviourType get_behaviourType();
}
