package com.example.contacts;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    @Insert
    void addContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    @Query("DELETE FROM contacts ")
    void deleteAllContacts();

    @Update
    void update(Contact contact);

}
