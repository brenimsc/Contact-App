package com.example.contactapp.sampledata

import com.example.contactapp.data.Contact
import java.util.Calendar

var contactSample: List<Contact> = listOf(
    Contact(
        name = "Ana",
        lastName = "Clara",
        phone = "123",
        photo = "https://images.pexels.com/photos/3922294/pexels-photo-3922294.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",

        ),
    Contact(
        name = "Bill",
        lastName = "Lima",
        phone = "321",
        photo = "https://images.pexels.com/photos/1415882/pexels-photo-1415882.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        birthday = Calendar.getInstance().time
    ),
    Contact(
        name = "Odes",
        lastName = "Conhecido",
        phone = "321",
        photo = "urlTesteParaDarErro"
    )
)