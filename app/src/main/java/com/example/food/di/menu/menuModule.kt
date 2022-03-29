package com.example.food.di.menu

import com.example.food.data.api.TheMealDBAPI
import com.example.food.data.repository.MenuRepositoryImpl
import com.example.food.domain.menu.MenuInteractor
import com.example.food.domain.repository.MenuRepository
import com.example.food.presentation.menu.mvvm.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val menuModule = module {
    single<MenuRepository> { MenuRepositoryImpl(get()) }
    single<MenuInteractor> { MenuInteractor(get()) }
    viewModel { MenuViewModel(get()) }
    single<Retrofit> { provideRetrofit() }
    single<TheMealDBAPI> { provideTheMealDBAPI(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideTheMealDBAPI(retrofit: Retrofit): TheMealDBAPI {
    return retrofit.create(TheMealDBAPI::class.java)
}
