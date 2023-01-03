package agh.ics.darvin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import static java.lang.Double.max;
import static java.lang.Double.min;

public class Animal extends AbstractMapElement{
    private AnimalManager manager;
    private static int startEnergy;
    private static int energyFromPlant;
    private static int minimalEnergyToReproduction;
    private static int reproductionCost;
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private int energy;
    private int children = 0;
    private int days = 0;
    private final Genome genome;

    public static void initAnimal(int startEnergy, int reproductionCost, int minimalEnergyToReproduction, int energyFromPlant){
        if (startEnergy > 0)
            Animal.startEnergy = startEnergy;
//        else
//            throw new IllegalArgumentException("energy must be higher than 0");
        if (minimalEnergyToReproduction > 0)
            Animal.minimalEnergyToReproduction = minimalEnergyToReproduction;
//        else throw new IllegalArgumentException("minimal energy to couple must be higher than 0");
        if (reproductionCost < minimalEnergyToReproduction)
            Animal.reproductionCost = reproductionCost;
//        else throw new IllegalArgumentException("reproduction cost must be lower than minimal value " + minimalEnergyToReproduction);
        if (energyFromPlant > 0)
            Animal.energyFromPlant = energyFromPlant;
//        else throw new IllegalArgumentException("energy provided by plant must be higher than 0");
    }

    public Animal(IWorldMap map){
        this(map, Vector2d.randomVectorInRectangle(map.getLowerLeft(), map.getUpperRight()), new Genome(), Animal.startEnergy);
    }
    public Animal(IWorldMap map, Vector2d initialPosition, Genome genome){
        this(map,initialPosition,genome,Animal.startEnergy);
    }
    public Animal(IWorldMap map, Vector2d initialPosition, Genome genome, int energy){
        this.map = map;
        this.position = initialPosition;
        this.energy = energy;
        this.genome = genome;
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }
    public String toString(){
        return this.orientation.toString();
    }

    public Boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public Boolean canReproduce(){
        return this.energy >= Animal.minimalEnergyToReproduction;
    }

    public void move(){
        this.energy--;
        if(this.energy == -1) {
            this.manager.animalDied(this);
            return;
        }
        this.days += 1;
        int rotations = genome.getGene();
        for (int i = 0; i < rotations; i++){
            this.orientation = this.orientation.next();
        }
        Vector2d oldPosition = this.position;
        this.position = map.getFinalPosition(this);
        positionChanged(oldPosition);
    }

    public Animal reproduce(Animal other){
        if (! (this.canReproduce() && other.canReproduce()))
            throw new IllegalArgumentException("those animals can not couple");
        this.children++;
        other.children++;
        Genome genome = Genome.genomeCreation(this.genome, this.energy, other.genome, other.energy);
        this.energy -= Animal.reproductionCost;
        other.energy -= Animal.reproductionCost;
        return new Animal(this.map, this.position, genome, 2 * Animal.reproductionCost);
    }

    private void positionChanged(Vector2d oldPosition){
        this.manager.animalChangedPosition(oldPosition, this);
    }
    public void decreaseEnergy(){
        this.energy = this.energy - Animal.reproductionCost;
    }
    public void feed(){
        this.energy += Animal.energyFromPlant;
    }
    public String getImageName(){
        return "";
    }

    @Override
    public Shape get_representation() {
        return new Circle(5, Color.color(min(energy/100.0, 1), min(energy/10, 1), min(energy, 1)));
    }

    public void setManager(AnimalManager manager){
        this.manager = manager;
    }
    public boolean isStronger(Animal animal){
        if(this.energy == animal.energy)
            if (this.days == animal.days)
                return this.children > animal.children;
            else return this.days > animal.days;
        else return this.energy > animal.energy;
    }
}
