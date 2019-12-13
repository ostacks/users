package com.dwp.utils;

/*
Source: https://www.geodatasource.com/developers/java
 */
public class DistanceCalculatorUtils {
    private static final double LONDON_LATITUDE = 51.509865;
    private static final double LONDON_LONGITUDE = -0.118092;

    public static boolean isWithinXMilesOfLondon(double latitude, double longitude, int miles) {
        return distance(LONDON_LATITUDE, LONDON_LONGITUDE, latitude, longitude) <= miles;
    }

    private static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {
        if ((latitude1 == latitude2) && (longitude1 == longitude2)) {
            return 0;
        } else {
            double theta = longitude1 - longitude2;
            double distance = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            distance = distance * 60 * 1.1515;

            return distance;
        }
    }
}