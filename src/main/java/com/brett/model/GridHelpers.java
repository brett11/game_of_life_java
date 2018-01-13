package com.brett.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridHelpers {
    public static List<Integer[]> TRADITIONAL_NEIGHBOR_COORDINATES = new ArrayList<>();

    // clockwise
    // 2, 0   2, 1   2, 2
    // 0, 0   0, 1   0, 2
    // 1, 0   1, 1   1, 2

    //set up using static initializer block per Learning Java pg 153
    static {
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{-1, -1});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{-1,  0});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{-1,  1});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{ 0, -1});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{ 0,  1});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{ 1, -1});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{ 1,  0});
        TRADITIONAL_NEIGHBOR_COORDINATES.add(new Integer[]{ 1,  1});
    }
}
