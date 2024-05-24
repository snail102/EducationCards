package ru.anydevprojects.educationcards.profile.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.anydevprojects.educationcards.profile.data.ProfileRepositoryImpl
import ru.anydevprojects.educationcards.profile.domain.ProfileRepository
import ru.anydevprojects.educationcards.profile.presentation.ProfileViewModel

val profileModule = module {
    viewModel { ProfileViewModel(get()) }
    factory<ProfileRepository> { ProfileRepositoryImpl(get(), get()) }
}
