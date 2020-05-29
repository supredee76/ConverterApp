package convert;

import java.util.*;

public class ConversionUtil {

    final private static String[] length = {"CM","IN","FT","YD"};
    final private static String[] weight = {"KG","LB"};
    final private static String[] liquid = {"L","G", "LB"};

    final private static double[][] lengthFactor = { 
        {1 , 0.3937007874 , 0.032808399 , 0.010936133},
        {2.54 , 1 , 0.0833333333 , 0.0277777778},
        {30.48 , 12 , 1 , 0.3333333333},
        {91.44 , 36 , 3 , 1}
    };

    final private static double[][] weightFactor = { 
        {1 , 2.2046244202},
        {0.453592 , 1}
    };

    final private static double[][] liquidFactor = { 
        {1 , 0.2641721769 , -1},
        {3.8 , 1 , 8},
        {-1 , 0.125 , 1}
    };

    public static double convert(double n, String from, String to){
        if(n >= 0){
            String[] cat = getCategory(from, to);
            double[][] factor = getFactor(from, to);
            int fromIndex = getUnitIndex(from, cat);
            int toIndex = getUnitIndex(to, cat);

            if(cat != null && factor != null && fromIndex != -1 && toIndex != -1){
                double ratio;
                if(from.equals(to)){
                    ratio = 1.0;
                } else {
                    ratio = factor[fromIndex][toIndex];
                } 
                if(ratio != -1.0){ return n * ratio; }
            }
        }
        return -1.0;
    }

    private static int getUnitIndex(String code, String[] unitList){
        if(code != null && unitList != null){
            for(int i = 0 ; i < unitList.length ; i++){
                String cd = unitList[i];
                if(code.equals(cd)){
                    return i;
                } 
            }
        }        
        return -1;
    }

    private static double[][] getFactor(String from, String to){
        String code = (!from.equals("LB")) ? from : to;
        switch(code){            
            case "CM":
            case "IN":
            case "FT":
            case "YD":
                return lengthFactor;
            case "KG":
                return weightFactor;
            case "L":
            case "G":
                return liquidFactor;
            default:
                return null;
        }
    }

    private static String[] getCategory(String from, String to){
        List<String[]> categories = new ArrayList<String[]>();
        categories.add(length);
        categories.add(weight);
        categories.add(liquid);
        for(String[] cat : categories){
            if(Arrays.asList(cat).contains(from) && Arrays.asList(cat).contains(to)){
                return cat;
            }
        }
        return null;
    }

}