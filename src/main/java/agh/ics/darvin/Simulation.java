package agh.ics.darvin;

import agh.ics.darvin.config.Config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation implements Runnable, IDeathsObserver {
    private boolean running = true;
    private final RectangularMap map;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Animal> deadAnimals = new ArrayList<>();
    private final Map<Vector2d, Animal> animalsToFeed = new HashMap<>();
    private final int dailyGrowth;
    private final IMapUpdateObserver parent;
    private int day;


    public Simulation(IMapUpdateObserver parent, Config config){
        this.parent = parent;
//        Animal.initAnimal(config.startEnergy, config.energyCost, config.minEnergy, config.energyFromPlant);
//        Genome.initGenome(config.genomeLength, config.maxMutationNum, config.minMutationNum, config.mutationType, config.behaviourType);
        this.map = new RectangularMap(config.width, config.height, config.boundaryType, config.forestType);
        for (int i = 0; i < config.plantsNum; i++) {
            this.map.seedPlant();
        }
        for (int i = 0; i < config.animalNum; i++) {
            Animal newAnimal = new Animal(this.map, config);
            animals.add(newAnimal);
            this.map.place(newAnimal);
        }
        map.addObserver(this);
        this.dailyGrowth = config.dailyGrowth;
    }

    private void killAnimals() {
        for (Animal animal : deadAnimals) {
            this.map.remove(animal);
            this.animals.remove(animal);
        }
        deadAnimals.clear();
    }

    private void tryAddAnimalToFeed(Animal animal) {
        Animal other = this.animalsToFeed.getOrDefault(animal.getPosition(), null);
        if (other == null) this.animalsToFeed.put(animal.getPosition(), animal);
        else if (animal.isStronger(other)) {
            this.animalsToFeed.remove(animal.getPosition());
            this.animalsToFeed.put(animal.getPosition(), animal);
        }
    }

    private void moveAnimals() {
        for (Animal animal : animals) {
            animal.move();
            if (this.map.plantAt(animal.getPosition()) != null)
                tryAddAnimalToFeed(animal);
        }
    }

    private void eatPlants() {
        for (Animal animal : this.animalsToFeed.values()) {
            this.map.feedAnimal(animal);
        }
    }

    private void animalsReproduction() {
        for (Animal child : this.map.genderAnimals()) {
            this.map.place(child);
            this.animals.add(child);
        }
    }

    private void plantsGrowth() {
        for (int i = 0; i < this.dailyGrowth; i++) this.map.seedPlant();
    }

    @Override
    public void run() {
        while (running) {
            single_step();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Simulation: interrupted");
                return;
            }
        }
    }

    public void single_step() {
        killAnimals();
        moveAnimals();
        eatPlants();
        animalsReproduction();
        plantsGrowth();
        day++;
        parent.mapChanged(map);
        System.out.println(this.map);
    }

    public void stopSimulation() {
        running = false;
    }

    public void startSimulation() {
        running = true;
        this.run();
    }

    public void endSimulation() {
        running = false;
    }

    @Override
    public void animalDied(Animal animal) {
        this.deadAnimals.add(animal);
    }

    public List<Animal> getAnimals(){
        return this.animals;
    }

    public int getDay() {
        return day;
    }
}
