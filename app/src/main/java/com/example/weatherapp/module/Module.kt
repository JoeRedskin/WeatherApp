package com.example.weatherapp.module

import com.example.weatherapp.model.CityDetailsViewModel
import com.example.weatherapp.model.CityViewModel
import com.example.weatherapp.module.Provide.provideDao
import com.example.weatherapp.module.Provide.provideDatabase
import com.example.weatherapp.module.Provide.provideGson
import com.example.weatherapp.module.Provide.provideRetrofit
import com.example.weatherapp.module.Provide.provideUserRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val cityViewModelModule: Module = module {
    viewModel { CityViewModel(get()) }
}

val cityDetailViewModelModule: Module = module {
    viewModel { (id: Int) -> CityDetailsViewModel(id) }
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
