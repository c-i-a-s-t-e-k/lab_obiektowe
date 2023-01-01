package agh.ics.darvin;

public abstract class AbstractMapElement implements IMapElement{
    protected Vector2d position;
    abstract public String getImageName();
    public Vector2d getPosition(){
        return this.position;
    }
}
