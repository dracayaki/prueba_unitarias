package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;
import static org.testng.Assert.*;

@Test
public class DirectionSpec {

    public void whenGetFromShortNameNThenReturnDirectionN() {

        Direction dir = Direction.NORTH;
        assertEquals(Direction.getFromShortName('N'), dir);
    }

    public void whenGetFromShortNameWThenReturnDirectionW() {

        Direction dir = Direction.WEST;
        assertEquals(Direction.getFromShortName('W'), dir);
    }

    public void whenGetFromShortNameBThenReturnNone() {
        Direction dir = Direction.NONE;
        assertEquals(Direction.getFromShortName('B'), dir);
    }

    public void givenSWhenLeftThenE() {
        Direction dir = Direction.SOUTH;
        assertEquals(dir.turnLeft(), Direction.EAST);
    }

    public void givenNWhenLeftThenW() {
        Direction dir = Direction.NORTH;
        assertEquals(dir.turnLeft(), Direction.WEST);
    }

    public void givenSWhenRightThenW() {
        Direction dir = Direction.SOUTH;
        assertEquals(dir.turnRight(), Direction.WEST);
    }

    public void givenWWhenRightThenN() {
        Direction dir = Direction.WEST;
        assertEquals(dir.turnRight(), Direction.NORTH);
    }

}
