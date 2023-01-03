package agh.ics.darvin;

import agh.ics.darvin.enums.BoundaryType;
import agh.ics.darvin.enums.ForestType;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap, IManager{
    public final int width;
    public final int height;
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final IBoundary boundary;
    private  final IForest forest;
    private final ElementsContainer container  = new ElementsContainer();
    private final MapVisualizer mapVisualizer = new  MapVisualizer(this);

    public RectangularMap(int width, int height, BoundaryType boundaryType, ForestType forestType){
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(this.width, this.height);
        this.boundary = boundaryType.getBoundary(this);
        this.forest = forestType.getForest(this);
    }

    public boolean canPutOn(Vector2d position) {
        return (position.precedes(upperRight) && (position.follows(lowerLeft)));
    }

    public Vector2d getLowerLeft(){
        return lowerLeft;
    }
    public Vector2d getUpperRight(){
        return upperRight;
    }

    @Override
    public Vector2d getFinalPosition(Animal animal) {
        Vector2d finalPosition = animal.getPosition().add(animal.getOrientation().toUnitVector());
        if(this.canPutOn(finalPosition)) return finalPosition;
        else return this.boundary.backToBoundary(animal);
    }

    @Override
    public boolean place(IMapElement mapElement) {
        if (canPutOn(mapElement.getPosition())){
            container.addElement(mapElement);
            return true;
        }
        else {
            throw new IllegalArgumentException("cannot place animal on position:" + mapElement.getPosition());
        }
    }
    public void remove(IMapElement mapElement){
        this.container.removeElement(mapElement);
    }
    public void seedPlant(){
        Vector2d position = forest.placeToGrow();
        if(position != null)
            place(new Plant(position));
    }

    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return elementsAt(position).length != 0;
    }

    @Override
    public IMapElement[] elementsAt(Vector2d position) {
        Plant plant = this.container.getPlant(position);
        IMapElement[] animals = this.container.getAnimals(position);
        if(plant == null){
            return animals;
        }
        else {
            IMapElement[] elements = new IMapElement[1 + animals.length];
            elements[0] = plant;
            System.arraycopy(animals, 0, elements, 1, elements.length - 1);
            return elements;
        }
    }
    public Plant plantAt(Vector2d position){
        return this.container.getPlant(position);
    }
    public void feedAnimal(Animal animal){
        Plant plant = plantAt(animal.getPosition());
        if (plant == null)
            return;
//            throw new IllegalArgumentException("animal cannot eat, there is no grass here" + animal.getPosition());
        this.container.removePlant(plant);
        animal.feed();
    }
    public Iterable<Animal> genderAnimals(){
        List<Animal> children = new ArrayList<>();
        for (Animal[] couple : this.container.getCouples()){
            children.add(couple[0].reproduce(couple[1]));
        }
        return children;
    }

    private boolean canBeObserver(Object observer){
        return (observer instanceof IDeathsObserver || observer instanceof IPlantObserver
                || observer instanceof IPositionChangeObserver);
    }
    public void addObserver(Object observer){
        if (canBeObserver(observer))
            container.addObserver(observer);
        else throw new IllegalArgumentException(observer + " can not be a observer of this map");
    }
    public void removeObserver(Object observer){
        if (canBeObserver(observer))
            container.removeObserver(observer);
        else throw new IllegalArgumentException(observer + " can not be a observer of this map");
    }
}
