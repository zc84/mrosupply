package com.utils;

import java.util.Random;

/**
 * Created by dys on 25.11.2014.
 */
public class Math {

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;

    }
}
