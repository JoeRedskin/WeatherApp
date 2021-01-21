package com.example.weatherapp

import com.example.weatherapp.Provide.provideDao
import com.example.weatherapp.Provide.provideDatabase
import com.example.weatherapp.Provide.provideGson
import com.example.weatherapp.Provide.provideRetrofit
import com.example.weatherapp.Provide.provideUserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { CityViewModel(get()) }
}

val netModule = module {
    single { provideGson() }
    single { provideRetrofit(get()) }
}

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repositoryModule = module {
    single { provideUserRepository(get(), get()) }
}
