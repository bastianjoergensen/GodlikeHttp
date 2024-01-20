package com.godlikehttp;

public class Helpers
{
    public static int getPercent(int cur, int max)
    {
        return (int)Math.ceil(100.0d * cur / max);
    }
}
