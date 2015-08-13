package nla.local.util;

import nla.local.pojos.rights.RightOwner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beresnev on 26.02.2015.
 */
@Component
public class Converter {

    public enum ConvertType{
        LAT2CYR, CYR2LAT
    }

    private static String[] listLat = null;
    private static String[] listCyr = null;


    @PostConstruct
    public void init() {
        String lat = "A B C E O H P T M";
        lat += " " + lat.toLowerCase();


        String cyr = "А В С Е О Н Р Т М";
        cyr += " " + cyr.toLowerCase();


        listLat = lat.split(" ");
        listCyr = cyr.split(" ");
    }

    public static String convertText(String line, ConvertType type){

        int i = 0;

        if(type == ConvertType.LAT2CYR)
            for(String item : listLat)
            {
                line = line.replaceAll(item, listCyr[i]);
                i++;
            }

        else if(type == ConvertType.CYR2LAT)
            for(String item : listCyr)
            {
                line = line.replaceAll(item, listLat[i]);
                i++;
            }

        return line;
    }

    public static <T> HashMap<T, T> getCastHash(ArrayList<T> key_list, ArrayList<T> val_list ) {

        HashMap<T,T> ret_val = new HashMap<T, T>();

        if( key_list != null && val_list != null && ( key_list.size() == val_list.size() ) ) {

            for (int i = 0; i < key_list.size(); i++) {
                ret_val.put(key_list.get(i), val_list.get(i));
            }
        }

        return ret_val;
    }
}
