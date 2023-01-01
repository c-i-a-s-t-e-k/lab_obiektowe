package agh.ics.darvin;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap{

    protected final ElementsContainer container  = new ElementsContainer();
    protected final MapVisualizer mapVisualizer = new  MapVisualizer(this);
    abstract public Vector2d getUpperRight();
    abstract public Vector2d getLowerLeft();
    @Override
    public boolean place(IMapElement mapElement) {
        if (canMoveTo(mapElement.getPosition())){
            if(mapElement instanceof Animal)
                ((Animal) mapElement).addObserver(this.container);
            container.addElement(mapElement);
            return true;
        }
        else {
            throw new IllegalArgumentException("cannot place animal on position:" + mapElement.getPosition());
        }

    }
    abstract public boolean canMoveTo(Vector2d position);

    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }


    public Vector2d getFinalPosition(Vector2d position, MapDirection orientation){
        return position.add(orientation.toUnitVector());
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return elementsAt(position).length != 0;
    }

    @Override
    public IMapElement[] elementsAt(Vector2d position) {
        Grass plant = this.container.getPlant(position);
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
}
