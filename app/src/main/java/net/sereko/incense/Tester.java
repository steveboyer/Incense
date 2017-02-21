package net.sereko.incense;

import java.util.Objects;

/**
 * Created by steve on 2/9/17.
 */
public class Tester {



    public Tester(){


    }




    public static Integer findApex(Integer[] values){
        if(values == null || values.length == 0) return null;

        Integer floor = 0;
        Integer ceiling = values.length;

        return checkForApex(values, floor, ceiling);

    }

    public static Integer checkForApex(Integer[] values,
                                       Integer floor,
                                       Integer ceiling){

        System.out.println("floor: " + floor);
        System.out.println("ceiling: " + ceiling);
        Integer nFloor, nCeiling, mid, next, before;
        mid = (ceiling - floor)/2 + floor;
        next = mid + 1;
        before = mid - 1;

        System.out.println(String.format("before = %d, mid = %d, next = %d", before, mid, next));

        if(floor < ceiling){

            if(values[floor] != null && values[next] != null){
                if(before > 0 && values[before] > values[mid]){
                    return before;
                }
                if(Objects.equals(next, ceiling)){
                    return mid;
                }
                if(values[mid] < values[next]){
                    Integer left = checkForApex(values, floor, mid);
                    Integer right = checkForApex(values, mid, ceiling);

                    if(left != null){
                        return left;
                    }
                    return right;

                } else if (values[mid] == values[next]){
                    return null;
                } else {
                    return mid;
                }

            }

        } else {
            return floor;
        }
        return null;
    }

//    public static Integer getthenum(Integer thisone){
//        System.out.println(thisone);
//        Integer nextone;
//        nextone = thisone + 1;
//
//        return getthenum(nextone);
//    }




}
