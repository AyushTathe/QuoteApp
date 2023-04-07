package com.example.eod;

public class Utils {
    private static final long SECOND_MILLIS  = 1000;
    private static final long MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final long DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String  getTimeAgo(long time)
    {
        long now = System.currentTimeMillis();
        if(time > now || time <= 0)
        {
            return null;
        }
        long diff = now-time;
        if(diff < MINUTE_MILLIS)
        {
            return "just now";
        }
        else if(diff < 2 * MINUTE_MILLIS)
        {
            return "an hour ago";
        }
        else if(diff < 50 * MINUTE_MILLIS)
        {

            return (diff/MINUTE_MILLIS)+" minutes ago";
        }
        else if(diff < 90 *MINUTE_MILLIS)
        {
            return "an hour ago";
        }
        else if(diff < 24 * HOUR_MILLIS)
        {
            return (diff/HOUR_MILLIS)+" hours ago";
        }
        else if(diff < 48 * HOUR_MILLIS)
        {
            return "yesterday";
        }
        else

            return  ( diff/DAY_MILLIS)+ " days ago";
    }
}
