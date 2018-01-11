package com.brett.model;

public class GridHelpers {
    public static int[][] TRADITIONAL_NEIGHBOR_COORDINATES = {
        // clockwise
        // 0, 0   0, 1   0, 2
        // 1, 0   1, 1   1, 2
        // 2, 0   2, 1   2, 2
        {-1, -1}, {-1, 0}, {-1, 1},
        { 0, -1},          { 0, 1},
        { 1, -1}, { 1, 0}, { 1, 1}
        };
}
