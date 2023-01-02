package agh.ics.darvin;

public class ToxicField implements Comparable<ToxicField>{
    private final Vector2d position;
    private int toxicity = 0;

    public ToxicField(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void increaseToxicity(){
        toxicity++;
    }
    public boolean equals(Object o){
        if (o instanceof ToxicField)
            return this.position.equals(((ToxicField) o).position);
        else return false;
    }

    @Override
    public int compareTo(ToxicField o) {
        return o.toxicity - this.toxicity;
    }
}
