package ru.anydevprojects.educationcards.profile.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import ru.anydevprojects.educationcards.profile.presentation.ProfileViewModel

val profileModule = module {
    viewModelOf(::ProfileViewModel)
}
