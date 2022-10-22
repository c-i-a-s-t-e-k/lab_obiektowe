package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OptionsPaserTest {

    @Test
    public void testParse(){
        MoveDirection[] moveDirTab = {MoveDirection.FORWARD, MoveDirection.FORWARD
                , MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        String[] stringTab1 = {"f", "forward", "b", "backward", "r", "right", "l", "left"};
        String[] stringTab2 = {"f", "impostor", "forward", "b", "left right", "backward", "r", "my life is a mess",
                "right", "l", "left"};
        String[] stringTab3 = {};

        Assertions.assertArrayEquals(OptionsParser.parse(stringTab1), moveDirTab);
        Assertions.assertArrayEquals(OptionsParser.parse(stringTab2), moveDirTab);
        Assertions.assertArrayEquals(OptionsParser.parse(stringTab3), new MoveDirection[0]);
    }
}
