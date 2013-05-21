package com.abushaev.GuestBook.GuestBook;

import java.util.List;

/**
 * Author: Abushaev Ruslan
 * e-mail: rouslan@inbox.ru
 * Date: 20.05.13
 * Time: 20:05
 */
public interface GuestBookController {
    void addRecord(String message);
    List<Record> getRecords();
}
