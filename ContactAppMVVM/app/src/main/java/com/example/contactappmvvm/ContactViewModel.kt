package com.example.contactappmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.contactappmvvm.data.database.getContactDatabase
import com.example.contactappmvvm.data.Contact

class ContactViewModel: ViewModel() {

    private val contactDao by lazy {
        getContactDatabase()
    }

    suspend fun getAll(): LiveData<List<Contact>> {
        return
    }
}