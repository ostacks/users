package com.dwp.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DistanceCalculatorUtilsTest {
    private static final double LEEDS_LATITUDE = 53.801277;
    private static final double LEEDS_LONGITUDE = -1.548567;
    private static final double CROYDON_LATITUDE = 51.376495;
    private static final double CROYDON_LONGITUDE = -0.100594;
    private static final double LONDON_LATITUDE = 51.509865;
    private static final double LONDON_LONGITUDE = -0.118092;

   @Test
    public void isWithinXMilesOfLondon(){
       assertTrue(DistanceCalculatorUtils.isWithinXMilesOfLondon(CROYDON_LATITUDE, CROYDON_LONGITUDE, 50));
       assertTrue(DistanceCalculatorUtils.isWithinXMilesOfLondon(LEEDS_LATITUDE, LEEDS_LONGITUDE, 500));
       assertTrue(DistanceCalculatorUtils.isWithinXMilesOfLondon(LONDON_LATITUDE, LONDON_LONGITUDE, 0));
   }

    @Test
    public void isNotWithinXMilesOfLondon(){
        assertFalse(DistanceCalculatorUtils.isWithinXMilesOfLondon(CROYDON_LATITUDE, CROYDON_LONGITUDE, 5));
        assertFalse(DistanceCalculatorUtils.isWithinXMilesOfLondon(LEEDS_LATITUDE, LEEDS_LONGITUDE, 50));
    }
}
