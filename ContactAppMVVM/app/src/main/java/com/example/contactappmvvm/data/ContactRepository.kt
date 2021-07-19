package com.example.contactappmvvm.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.contactappmvvm.data.local.ContactDao
import com.example.contactappmvvm.data.local.ContactDatabase

class ContactRepository(application: Application) {
    private val contactDatabase = ContactDatabase.getInstance(application)
    private val contactDao: ContactDao = contactDatabase!!.contactDao()


    suspend fun getAll(): LiveData<List<Contact>> {
        return contactDao.getAll()
    }

    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun delete(contact: Contact){
        contactDao.delete(contact)
    }
}