package org.iesvdm.tddjava.ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
//        ship = new Ship(location);
        ship = new Ship(location, planet);
    }

    @Test
    public void whenInstantiatedThenLocationIsSet() {
        assertEquals(ship.getLocation(), location);

    }

    @Test
  public void givenNorthWhenMoveForwardThenYDecreases() {
        Location loc = new Location(new Point(21, 13), Direction.NORTH);
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getY(), 12);
   }

   @Test
    public void givenEastWhenMoveForwardThenXIncreases() {
        Location loc = new Location(new Point(21, 13), Direction.EAST);
        ship.moveForward();

        //deberia devolver 22, pero devuelve 21
        assertEquals(ship.getLocation().getPoint().getX(), 22);
    }


    @Test
    public void whenMoveForwardThenForward() {
        assertEquals(ship.moveForward(), location.forward());
    }
    @Test
    public void whenMoveBackwardThenBackward() {
        assertEquals(ship.moveBackward(), location.backward());
    }
    @Test
    public void whenTurnLeftThenLeft() {
        Location copia = location.copy();
        ship.turnLeft();
        assertEquals(ship.getLocation().getDirection(), copia.getDirection().turnLeft());
    }
    @Test
    public void whenTurnRightThenRight() {
        Location copia = location.copy();
        ship.turnRight();
        assertEquals(ship.getLocation().getDirection(), copia.getDirection().turnRight());
    }
    @Test
    public void whenReceiveCommandsFThenForward() {
        Location copia = location.copy();
        String res = ship.receiveCommands("f");
        assertEquals(res, "O");
    }
    @Test
    public void whenReceiveCommandsBThenBackward() {
        String res = ship.receiveCommands("b");
        assertEquals(res, "O");
    }
    @Test
    public void whenReceiveCommandsLThenTurnLeft() {
        String res = ship.receiveCommands("l");
        assertEquals(res, "O");
    }
    @Test
    public void whenReceiveCommandsRThenTurnRight() {
        String res = ship.receiveCommands("r");
        assertEquals(res, "O");
    }
    @Test
    public void whenReceiveCommandsThenAllAreExecuted() {
        String res = ship.receiveCommands("fblr");
        assertEquals(res, "OOOO");
    }
    @Test
    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(ship.getPlanet(), planet);

    }
    @Test
    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        location = new Location(new Point(50, 13), Direction.EAST);
        ship = new Ship(location, planet);
        ship.moveForward();
        assertEquals(ship.getLocation().getPoint().getX(), 1);
    }
    @Test
    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        location = new Location(new Point(1, 13), Direction.EAST);
        ship = new Ship(location, planet);
        ship.moveBackward();
        assertEquals(ship.getLocation().getPoint().getX(), 50);
    }
    @Test
    public void whenReceiveCommandsThenStopOnObstacle() {

    }
    @Test
    public void whenReceiveCommandsThenOForOkAndXForObstacle() {

    }

}
