package com.abushaev.GuestBook.GuestBook;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.text.DateFormat;
import java.util.Date;

/**
 * Author: Abushaev Ruslan
 * e-mail: rouslan@inbox.ru
 * Date: 20.05.13
 * Time: 20:07
 */
public class Record {
    private long ID;
    private Date date;
    private String message;


    public Record(long ID, Date date, String message) {
        this.ID = ID;
        this.date = date;
        this.message = message;
    }

    public long getID() {
        return ID;
    }

    public String getDateStringFull() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.DEFAULT);
        return dateFormatter.format(date);
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
