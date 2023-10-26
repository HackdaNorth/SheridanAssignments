import lux.*;

import java.util.ArrayList;
import java.util.Collection;

public class map {


    private ArrayList<Cell> resourceTiles = new ArrayList<>();
    private Collection<City> city_tiles;
    private ArrayList<CityTile> citySurrTiles = new ArrayList<>();
    private boolean build_city = false;
    private Cell buildLocation;


    public map() {

    }

    public void generateResources(GameMap gameMap) {
        for (int y = 0; y < gameMap.height; y++) {
            for (int x = 0; x < gameMap.width; x++) {
                Cell cell = gameMap.getCell(x, y);
                if (cell.hasResource()) {
                    resourceTiles.add(cell);
                }
            }
        }
    }
    public void setCityTiles(Player player) {
        //set all the current players cities
        setCity_tiles(getPlayerCities(player));
//
//        //for each city in all cities
//        for (City city : getCity_tiles()) {
//            //for each tile surrounding the area
//            for(CityTile tile : city.citytiles) {
//                //add it to the arraylist
//                citySurrTiles.add(tile);
//                //set the list
//                setCitySurrTiles(citySurrTiles);
//            }
//        }
//        //return the list of surrounding tiles

    }



    public boolean buildNewCity() {
        if(getCity_tiles().size() < 2) {
            setBuild_city(true);
        }
        return getBuild_city();
    }

    public boolean getBuild_city() {
        return build_city;
    }

    public void setBuild_city(boolean build_city) {
        this.build_city = build_city;
    }

    public Cell getBuildLocation() {
        return buildLocation;
    }
    public void setBuildLocation(Cell buildLocation) {
        this.buildLocation = buildLocation;
    }

    public ArrayList<Cell> getResourceTiles() {
        return resourceTiles;
    }

    public void setResourceTiles(ArrayList<Cell> resourceTiles) {
        this.resourceTiles = resourceTiles;
    }

    public Collection<City> getPlayerCities(Player player) {
        Collection<City> allCities = player.cities.values();
       return allCities;
    }

    public Collection<City> getCity_tiles() {
        return city_tiles;
    }

    public void setCity_tiles(Collection<City> allCities) {
        this.city_tiles = allCities;
    }

    public ArrayList<CityTile> getCitySurrTiles() {
        return citySurrTiles;
    }

    public void setCitySurrTiles(ArrayList<CityTile> citySurrTiles) {
        this.citySurrTiles = citySurrTiles;
    }

}
