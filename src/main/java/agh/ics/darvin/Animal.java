package agh.ics.darvin;

import agh.ics.darvin.config.AnimalConfig;
import agh.ics.darvin.config.Config;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import static java.lang.Double.max;
import static java.lang.Double.min;

public class Animal extends AbstractMapElement{
    private AnimalManager manager;
    private final AnimalConfig config;
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private int energy;
    private int children = 0;
    private int days = 0;
    private final Genome genome;
    private boolean alive = true;
    public Animal(IWorldMap map, Config config){
        this(map, Vector2d.randomVectorInRectangle(map.getLowerLeft(), map.getUpperRight()), new Genome(config), config.getStartEnergy(), config);
    }
    public Animal(IWorldMap map, Vector2d initialPosition, Genome genome, int energy, AnimalConfig config){
        this.map = map;
        this.position = initialPosition;
        this.energy = energy;
        this.genome = genome;
        this.config = config;
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
        return this.energy >= config.getMinimalEnergyToReproduction();
    }

    public void move(){
        this.energy--;
        if(this.energy < 0) {
            this.manager.animalDied(this);
            alive = false;
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
        Genome genome = this.genome.mergeWith(other.genome, this.energy, other.energy);
        this.energy -= config.getReproductionCost();
        other.energy -= config.getReproductionCost();
        return new Animal(this.map, this.position, genome, 2 * config.getReproductionCost(), config);
    }

    private void positionChanged(Vector2d oldPosition){
        this.manager.animalChangedPosition(oldPosition, this);
    }
    public void decreaseEnergy(){
        this.energy = this.energy - config.getReproductionCost();
    }
    public void feed(){
        this.energy += config.getEnergyFromPlant();
    }
    public String getImageName(){
        return "";
    }

    @Override
    public Shape get_representation(SetAnimalTarget setAnimal) {
        var circle = new Circle(5, Color.color(max(min(energy/100.0, 1),0),0,0));
        var this_animal = this;
        circle.setOnMouseClicked((EventHandler) event -> setAnimal.setAnimal(this_animal));
        return circle;
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

    public String getInfo() {
        var str =  "Genome : " + genome.toString()
                + "\nActivated gene: " + genome.getGene();
        if (isAlive())
            str +="\nEnergy : " + energy;
        else
            str += "\nDEAD";
        return str;
    }
    public boolean isAlive(){
        return alive;
    }
}
