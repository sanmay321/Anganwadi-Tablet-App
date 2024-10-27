package com.anganwadi.app.di

import com.anganwadi.app.repository.UserRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get(named("userRef"))) }
}