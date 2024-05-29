package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class LocationSpec {

    private final int x = 12;
    private final int y = 32;
    private final Direction direction = Direction.NORTH;
    private Point max;
    private Location location;
    private List<Point> obstacles;

    @BeforeMethod
    public void beforeTest() {
        max = new Point(50, 50);
        location = new Location(new Point(x, y), direction);
        obstacles = new ArrayList<Point>();
    }

    public void whenInstantiatedThenXIsStored() {
        assertEquals(location.getX(), x);
    }

    public void whenInstantiatedThenYIsStored() {
        assertEquals(location.getY(), y);
    }

    public void whenInstantiatedThenDirectionIsStored() {
        assertEquals(location.getDirection(), direction);
    }

    public void givenDirectionNWhenForwardThenYDecreases() {
        location.forward();
        assertEquals(location.getY(), y-1);
    }

    public void givenDirectionSWhenForwardThenYIncreases() {
        Direction dir = Direction.SOUTH;
        Location loc = new Location(new Point(x, y), dir);

        loc.forward();
        assertEquals(loc.getY(), y+1);
    }

    public void givenDirectionEWhenForwardThenXIncreases() {
        Direction dir = Direction.EAST;
        Location loc = new Location(new Point(x, y), dir);

        loc.forward();
        assertEquals(loc.getX(), x+1);
    }

    public void givenDirectionWWhenForwardThenXDecreases() {
        Direction dir = Direction.WEST;
        Location loc = new Location(new Point(x, y), dir);

        loc.forward();
        assertEquals(loc.getX(), x-1);
    }

    public void givenDirectionNWhenBackwardThenYIncreases() {
        Direction dir = Direction.NORTH;
        Location loc = new Location(new Point(x, y), dir);

        loc.backward();
        assertEquals(loc.getY(), y+1);
    }

    public void givenDirectionSWhenBackwardThenYDecreases() {
        Direction dir = Direction.SOUTH;
        Location loc = new Location(new Point(x, y), dir);

        loc.backward();
        assertEquals(loc.getY(), y-1);
    }

    public void givenDirectionEWhenBackwardThenXDecreases() {
        Direction dir = Direction.EAST;
        Location loc = new Location(new Point(x, y), dir);

        loc.backward();
        assertEquals(loc.getX(), x-1);
    }

    public void givenDirectionWWhenBackwardThenXIncreases() {
        Direction dir = Direction.WEST;
        Location loc = new Location(new Point(x, y), dir);

        loc.backward();
        assertEquals(loc.getX(), y+1);
    }

    public void whenTurnLeftThenDirectionIsSet() {
        location.turnLeft();
        assertEquals(location.getDirection(), direction.turnLeft());
    }

    public void whenTurnRightThenDirectionIsSet() {
        location.turnRight();
        assertEquals(location.getDirection(), direction.turnRight());
    }

    public void givenSameObjectsWhenEqualsThenTrue() {
        Location loc1 = new Location(new Point(x, y), direction);
        assertTrue(location.equals(loc1));
    }

    public void givenDifferentObjectWhenEqualsThenFalse() {
        Location loc2 = new Location(new Point(x, y), Direction.WEST);
        assertFalse(location.equals(direction));
    }

    public void givenDifferentXWhenEqualsThenFalse() {
        Location loc2 = new Location(new Point(9, y), direction);
        assertFalse(location.equals(loc2));
    }

    public void givenDifferentYWhenEqualsThenFalse() {
        Location loc2 = new Location(new Point(x, 10), direction);
        assertFalse(location.equals(loc2));
    }

    public void givenDifferentDirectionWhenEqualsThenFalse() {
        Location loc2 = new Location(new Point(x, y), Direction.WEST);
        assertFalse(location.equals(loc2));
    }

    public void givenSameXYDirectionWhenEqualsThenTrue() {
        Location loc1 = new Location(new Point(x, y), direction);
        assertTrue(location.equals(loc1));
    }

    public void whenCopyThenDifferentObject() {
        Location copia = location.copy();
        assertNotSame(location, copia);
        }

    public void whenCopyThenEquals() {
        Location copia = location.copy();
        assertTrue(location.equals(copia));

    }

    public void givenDirectionEAndXEqualsMaxXWhenForwardThen1() {
        Location loc = new Location(new Point(max.getX(), y), Direction.EAST);
        loc.forward(max);
        assertEquals(loc.getX(), 1);
    }

    public void givenDirectionWAndXEquals1WhenForwardThenMaxX() {
        Location loc = new Location(new Point(1, y), Direction.WEST);
        loc.forward(max);
        assertEquals(loc.getX(), max.getX());
    }

    public void givenDirectionNAndYEquals1WhenForwardThenMaxY() {
        Location loc = new Location(new Point(x, 1), Direction.NORTH);
        loc.forward(max);
        assertEquals(loc.getY(), max.getY());
    }

    public void givenDirectionSAndYEqualsMaxYWhenForwardThen1() {
        Location loc = new Location(new Point(x, max.getY()), Direction.SOUTH);
        loc.forward(max);
        assertEquals(loc.getY(), 1);
    }

    public void givenObstacleWhenForwardThenReturnFalse() {
        Point obstaculo = new Point(x, y+1);
        obstacles.add(obstaculo);

        boolean res = location.forward(max, obstacles);

        //devuelve true, pero deberia devolver false, ya que el movimiento no
        //se ha podido hacer
    }

    public void givenObstacleWhenBackwardThenReturnFalse() {
        Point obstaculo = new Point(x, y-1);
        obstacles.add(obstaculo);

        boolean res = location.backward(max, obstacles);

        //devuelve true, pero deberia devolver false, ya que el movimiento no
        //se ha podido hacer
        assertFalse(res);
    }

}
