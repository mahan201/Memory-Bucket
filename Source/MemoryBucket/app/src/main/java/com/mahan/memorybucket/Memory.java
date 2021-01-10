package com.mahan.memorybucket;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TreeMap;

public class Memory {
    String title, content, dateString;
    LocalDate date;
    int rating;
    boolean isEditing, isExpanded;

    public Memory(String mTitle, String mContent, LocalDate mDate, int mRating){
        title = mTitle;
        content = mContent;
        date = mDate;
        rating = mRating;

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM yyyy");

        dateString = date.format(myFormatObj);
        isExpanded = false;
        isEditing = true;
    }

    public Memory(String stuff){
        String[] vals = stuff.split(",");
        title = vals[0];
        content = vals[1];
        date = LocalDate.parse(vals[2]);
        rating = Integer.parseInt(vals[3]);

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMM yyyy");

        dateString = date.format(myFormatObj);
        isExpanded = false;
        isEditing = false;
    }

    public String toString(){
        return (title + "," + content + "," + date.toString() + "," + rating + "," + dateString);
    }
}
