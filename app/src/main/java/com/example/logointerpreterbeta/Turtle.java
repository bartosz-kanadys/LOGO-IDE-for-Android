package com.example.logointerpreterbeta;


import android.graphics.Color;

public class Turtle {
    public static float Xposition = 1000;
    public static float Yposition = 1000;
    public static float direction = 0;
    public static boolean isDown = true;
    public static boolean isShowed = true;
    public static int penColor = Color.BLACK;
    public static int penSize = 5;

    public static void setAcctualPosition(float X, float Y) {
        Xposition = X;
        Yposition = Y;
    }

    public static void setPlusDirection(float rotate) {
        if (direction + rotate > 360) {
            direction = (direction + rotate) % 360;
        } else {
            direction += rotate;
        }
    }

    public static void setMinusDirection(float rotate) {
        if (direction - rotate < 360) {
            direction = (direction - rotate) % 360;
        } else {
            direction -= rotate;
        }
    }

}
