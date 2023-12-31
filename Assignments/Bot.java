import java.util.ArrayList;
import java.util.Collection;

import lux.*;

public class Bot {
    public static void main(final String[] args) throws Exception {
        Agent agent = new Agent();
        // initialize
        agent.initialize();
        while (true) {
            /** Do not edit! **/
            // wait for updates
            agent.update();

            ArrayList<String> actions = new ArrayList<>();
            GameState gameState = agent.gameState;
            /** AI Code Goes Below! **/

            Player player = gameState.players[gameState.id];
            Player opponent = gameState.players[(gameState.id + 1) % 2];
            GameMap gameMap = gameState.map;

            ArrayList<Cell> resourceTiles = new ArrayList<>();
            for (int y = 0; y < gameMap.height; y++) {
                for (int x = 0; x < gameMap.width; x++) {
                    Cell cell = gameMap.getCell(x, y);
                    if (cell.hasResource()) {
                        resourceTiles.add(cell);
                    }
                }
            }

            // we iterate over all our units and do something with them
            for (int i = 0; i < player.units.size(); i++) {
                Unit unit = player.units.get(i);
                if (unit.isWorker() && unit.canAct()) {
                    if (unit.getCargoSpaceLeft() > 0) {
                        // if the unit is a worker and we have space in cargo, lets find the nearest resource tile and try to mine it
                        Cell closestResourceTile = null;
                        double closestDist = 9999999;
                        for (Cell cell : resourceTiles) {

                            if (cell.resource.type.equals(GameConstants.RESOURCE_TYPES.COAL) && !player.researchedCoal()) continue;
                            if (cell.resource.type.equals(GameConstants.RESOURCE_TYPES.URANIUM) && !player.researchedUranium()) continue;
                            double dist = cell.pos.distanceTo(unit.pos);
                            if (dist < closestDist) {
                                closestDist = dist;
                                closestResourceTile = cell;
                            }
                        }

                        if (closestResourceTile != null) {
                            Direction dir = unit.pos.directionTo(closestResourceTile.pos);
                            // move the unit in the direction towards the closest resource tile's position.
                            actions.add(unit.move(dir));
                        }
                    } else {
                        boolean build_city  = false;
                        Cell build_location = null;

                        if(player.cities.values().size() < 2) {
                            build_city = true;
                        }



                        if(build_city) {

                            CityTile closeCity = getCityTile(player, unit);

                            int x = closeCity.pos.x;
                            int y = closeCity.pos.y;

                            Cell pickedTile = gameState.map.getCell(x,y);

                            if(!pickedTile.hasResource() & pickedTile.citytile == null & pickedTile.road == 0) {
                                build_location = pickedTile;
                            }



                        }else if (unit.pos.x == build_location.pos.x & unit.pos.y == build_location.pos.y) {
                            System.out.println("built a city");
                            build_city = false;
                            build_location = null;
                            actions.add(unit.buildCity());
                        }else {
                            Direction dir = unit.pos.directionTo(build_location.pos);
                            actions.add(unit.move(dir));
                            System.out.println("Sent move command" + unit.move(dir));
                        }


                        // if unit is a worker and there is no cargo space left, and we have cities, lets return to them
                        if (player.cities.size() > 0) {
                            City city = player.cities.values().iterator().next();
                            double closestDist = 999999;
                            CityTile closestCityTile = null;
                            for (CityTile citytile : city.citytiles) {
                                double dist = citytile.pos.distanceTo(unit.pos);
                                if (dist < closestDist) {
                                    closestCityTile = citytile;
                                    closestDist = dist;
                                }
                            }
                            if (closestCityTile != null) {
                                Direction dir = unit.pos.directionTo(closestCityTile.pos);
                                actions.add(unit.move(dir));
                            }
                        }
                    }
                }
            }

            // you can add debug annotations using the static methods of the Annotate class.
            // actions.add(Annotate.circle(0, 0));

            /** AI Code Goes Above! **/

            /** Do not edit! **/
            StringBuilder commandBuilder = new StringBuilder("");
            for (int i = 0; i < actions.size(); i++) {
                if (i != 0) {
                    commandBuilder.append(",");
                }
                commandBuilder.append(actions.get(i));
            }
            System.out.println(commandBuilder.toString());
            // end turn
            agent.endTurn();

        }
    }
    public static CityTile getCityTile(Player player, Unit unit) {
        double closestDist = 999999;
        CityTile closestCityTile = null;
        for (City city : player.cities.values()) {
            for (CityTile citytile : city.citytiles) {
                double dist = citytile.pos.distanceTo(unit.pos);
                if (dist < closestDist) {
                    closestCityTile = citytile;
                    closestDist = dist;
                }
            }
        }

        return closestCityTile;
    }
}