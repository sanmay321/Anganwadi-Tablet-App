package com.anganwadi.app.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AppModule = module {
    single(named("userRef")) { FirebaseDatabase.getInstance().getReference("users") }
}