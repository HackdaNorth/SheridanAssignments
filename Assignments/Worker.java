import lux.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Worker {
    //private Cell pickedTile;

    public Worker() {

    }

    public ArrayList<String> workerLogic(Player player, map map, PositionCheck ps, Resource rs,
                                         ArrayList<String> actions, GameState gameState) {
        for (int i = 0; i < player.units.size(); i++) {
            Unit unit = player.units.get(i);
            if (unit.isWorker() && unit.canAct()) {
                //if unit is full then lets go back to city
                if (unit.getCargoSpaceLeft() == 0) {
                    System.out.println("Worker is NOT FULL");
                    map.setCityTiles(player);
                    // we want to build
                    map.setBuild_city(false);
                    map.buildNewCity();
                    //if we want to build a city

                    if (map.getBuildLocation() == null) {
                        //get close city tile
                        CityTile closeCity = ps.getCloseCity(player, ps, unit);
                        System.out.println("CLose City  " + closeCity.pos);
                        // if unit is a worker and there is no cargo space left, and we have cities, lets return to them
                        //set the picked
                        int newX = closeCity.pos.x + 1;
                        int newY = closeCity.pos.y + 1;

                        Cell pickedTile = gameState.map.getCell(newX,newY);

                        if (!pickedTile.hasResource() & pickedTile.citytile == null & pickedTile.road == 0) {
                            map.setBuildLocation(pickedTile);
                            break;
                        }
                    }

                    if (unit.pos == (map.getBuildLocation().pos)) {

                        System.out.println("built a city");
                        map.setBuild_city(false);
                        map.setBuildLocation(null);
                        actions.add(unit.buildCity());

                    } else {
                        Direction dir = unit.pos.directionTo(map.getBuildLocation().pos);
                        actions.add(unit.move(dir));
                        System.out.println("Sent move command" + unit.move(dir));
                    }

                    CityTile closestTile = ps.getCloseCity(player, ps, unit);

                    System.out.println("Going Back home" + closestTile.pos);
                    Direction dir = unit.pos.directionTo(closestTile.pos);
                    actions.add(unit.move(dir));

                } else {

                    // if the unit is a worker and we have space in cargo, lets find the nearest resource tile and try to mine it
                    Cell cell = rs.getCloseResource(unit, map, player, ps);
                    if (cell != null) {
                        Direction dir = unit.pos.directionTo(cell.pos);
                        // move the unit in the direction towards the closest resource tile's position.
                        actions.add(unit.move(dir));
                    }




                    //get the build location as long as we want to build]
                }
            }
        }
        return actions;
    }

}
