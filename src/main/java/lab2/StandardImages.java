package lab2;

import java.util.ArrayList;
import java.util.List;

public class StandardImages {

    public static final Integer DIMENSION = 9;
    public static final Integer BLACK = 1;
    public static final Integer WHITE = -1;

    public static final List<List<Integer>> standardImagesBits = new ArrayList<>();

    static {
        //Г
        List<Integer> list1 = List.of(1,1,1,1,1,1,1,1,1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1,
                                                1,-1,-1,-1,-1,-1,-1,-1,-1);
        //T
        List<Integer> list2 = List.of(1,1,1,1,1,1,1,1,1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1,
                                                -1,-1,-1,-1,1,-1,-1,-1,-1);
        //Щ
        List<Integer> list3 = List.of(1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,-1,-1,1,-1,-1,1,-1,-1,
                                                1,1,1,1,1,1,1,1,1,
                                                -1,-1,-1,-1,-1,-1,-1,-1,1);

        standardImagesBits.addAll(List.of(list1, list2, list3));
    }


}