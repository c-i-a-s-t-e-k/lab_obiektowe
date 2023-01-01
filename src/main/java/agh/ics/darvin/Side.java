package agh.ics.darvin;

enum Side{
    Left,
    Right;

    static public Side randomSide(){
        if ((int)(Math.random() + 0.5) == 0) return Left;
        else return Right;
    }
}