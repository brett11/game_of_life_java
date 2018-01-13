package com.brett.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridHelpers {
    public static List<Integer[]> TRADITIONAL_NEIGHBOR_COORDINATES = getTraditionalNeighborCoordinates();

    // clockwise
    // 2, 0   2, 1   2, 2
    // 0, 0   0, 1   0, 2
    // 1, 0   1, 1   1, 2
    private static List<Integer[]> getTraditionalNeighborCoordinates(){
        List<Integer[]> result = new ArrayList<>();
        result.add(new Integer[]{-1, -1});
        result.add(new Integer[]{-1,  0});
        result.add(new Integer[]{-1,  1});
        result.add(new Integer[]{ 0, -1});
        result.add(new Integer[]{ 0,  1});
        result.add(new Integer[]{ 1, -1});
        result.add(new Integer[]{ 1,  0});
        result.add(new Integer[]{ 1,  1});
        return result;
    }
}
