package sheridan;

import sheridan.LiquourType;

import java.util.ArrayList;
import java.util.List;

public class LiquourService {
	

    public List<String> getAvailableBrands(LiquourType type){

    	List<String> brands = new ArrayList( );

        if(type.equals(LiquourType.WINE)){
            brands.add("Adrianna Vineyard");
            brands.add(("J. P. Chenet"));
            brands.add("Chenet");
            brands.add("Barefoot");
            brands.add("Spunmante Bambino");
            brands.add("Yellowglen");
            

        }else if(type.equals(LiquourType.WHISKY)){
            brands.add("Glenfiddich");
            brands.add("Johnnie Walker");
            brands.add("Jack Daniels");
            brands.add("Seagrams");
            brands.add("Buchanan's");

        }else if(type.equals(LiquourType.BEER)){
            brands.add("Corona");
            brands.add("Budwiser");
            brands.add("Heineken");
            brands.add("Coors");
            brands.add("Canadian");
            
        }else {
            brands.add("No Brand Available");
        }
    return brands;
    }
}
