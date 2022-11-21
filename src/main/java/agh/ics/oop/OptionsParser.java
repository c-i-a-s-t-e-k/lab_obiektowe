package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    static MoveDirection[] parse(String[] moves) {
        MoveDirection[] moveDirections = new MoveDirection[moves.length + 1];
        int i = 0;
        for (String move : moves) {
            switch (move) {
                case "f":
                case "forward":
                    moveDirections[i] = MoveDirection.FORWARD;
                    i = i + 1;
                    break;
                case "b":
                case "backward":
                    moveDirections[i] = MoveDirection.BACKWARD;
                    i = i + 1;
                    break;
                case "l":
                case "left":
                    moveDirections[i] = MoveDirection.LEFT;
                    i = i + 1;
                    break;
                case "r":
                case "right":
                    moveDirections[i] = MoveDirection.RIGHT;
                    i = i + 1;
                    break;
                default:
                    throw new IllegalArgumentException(move + " is not legal move specification");
            }
        }
        return Arrays.copyOfRange(moveDirections, 0, i);
    }

}
