package com.example.logointerpreterbeta;


import android.graphics.Color;

public class Turtle {
    public static float Xposition = 500;
    public static float Yposition = 500;
    public static float direction = 0;
    public static boolean isDown = true;
    public static boolean isShowed = true;
    public static int penColor = Color.BLACK;
    public static int penSize = 5;

    public static void setAcctualPosition(float X, float Y) {
        Xposition = X;
        Yposition = Y;
    }
}
