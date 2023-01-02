package agh.ics.darvin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation implements Runnable, IDeathsObserver{
    private boolean running = true;
    private static boolean simulationEnded = true;
    private final RectangularMap map;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Animal> deadAnimals = new ArrayList<>();
    private final Map<Vector2d, Animal> animalsToFeed = new HashMap<>();
    private final int dailyGrowth;

    public Simulation(){
        this(20, 10, ForestType.EQUATORIAL_FOREST, BoundaryType.PLANET, 10, 10
                , 30, 10, 40, 25, 20, 0, 0
                , MutationType.SLIGHT_CHANGE, 10, BehaviourType.FULL_PREDESTINATION);
    }
    public Simulation(int width, int height, ForestType forestType, BoundaryType boundaryType, int plantsNum,
                      int energyFromPlant, int dailyGrowth, int animalNum, int startEnergy, int minEnergy,
                      int energyCost, int minMutationNum, int maxMutationNum, MutationType mutationType,
                      int genomeLength, BehaviourType behaviourType){
        if(!simulationEnded) throw new IllegalStateException("can not start new simulation if another is still running");
        Simulation.simulationEnded = false;
        Animal.initAnimal(startEnergy, energyCost, minEnergy, energyFromPlant);
        Genome.initGenome(genomeLength, maxMutationNum, minMutationNum, mutationType, behaviourType);
        this.map = new RectangularMap(width, height, boundaryType, forestType);
        for (int i = 0; i<plantsNum; i++){
            this.map.seedPlant();
        }
        for (int i = 0; i < animalNum; i++){
            Animal newAnimal = new Animal(this.map);
            animals.add(newAnimal);
            this.map.place(newAnimal);
        }
        map.addObserver(this);
        this.dailyGrowth = dailyGrowth;
    }



    private void killAnimals(){
        for (Animal animal : deadAnimals){
            this.map.remove(animal);
            this.animals.remove(animal);
        }
        deadAnimals.clear();
    }
    private void tryAddAnimalToFeed(Animal animal){
        Animal other = this.animalsToFeed.getOrDefault(animal.getPosition(), null);
        if (other == null) this.animalsToFeed.put(animal.getPosition(), animal);
        else if (animal.isStronger(other)) {
            this.animalsToFeed.remove(animal.getPosition());
            this.animalsToFeed.put(animal.getPosition(), animal);
        }
    }
    private void moveAnimals(){
        for (Animal animal : animals){
            animal.move();
            if (this.map.plantAt(animal.getPosition()) != null)
                tryAddAnimalToFeed(animal);
        }
    }
    private void eatPlants(){
        for (Animal animal : this.animalsToFeed.values()){
            this.map.feedAnimal(animal);
        }
    }
    private void animalsReproduction(){
        for (Animal child : this.map.genderAnimals()){
            this.map.place(child);
            this.animals.add(child);
        }
    }
    private void plantsGrowth(){
        for (int i = 0; i < this.dailyGrowth; i++) this.map.seedPlant();
    }

    @Override
    public void run() {
        while (running){
//            try {
                killAnimals();
//            }catch (NullPointerException e){
//                System.out.println(e.getMessage());
//                for (Animal animal : deadAnimals)
//                    System.out.println(animal.getPosition().toString());
//                stopSimulation();
//            }
            moveAnimals();
            eatPlants();
            animalsReproduction();
            plantsGrowth();
            System.out.println(this.map);
        }
    }
    public void stopSimulation(){
        running = false;
    }
    public void startSimulation() {
        running = true;
        this.run();
    }
    public void endSimulation(){
        running = false;
        Simulation.simulationEnded = true;
    }
    @Override
    public void animalDied(Animal animal) {
        this.deadAnimals.add(animal);
    }
}
