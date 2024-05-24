package ru.anydevprojects.educationcards.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.anydevprojects.educationcards.core.mvi.MVI
import ru.anydevprojects.educationcards.core.mvi.mvi
import ru.anydevprojects.educationcards.profile.domain.ProfileRepository
import ru.anydevprojects.educationcards.profile.presentation.models.EventProfile
import ru.anydevprojects.educationcards.profile.presentation.models.IntentProfile
import ru.anydevprojects.educationcards.profile.presentation.models.StateProfile

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel(),
    MVI<StateProfile, IntentProfile, EventProfile> by mvi(
        StateProfile()
    ) {

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val isDeckStatisticsAvailable = profileRepository.isDeckStatisticsAvailable()
            val isCardStatisticsAvailable = profileRepository.isCardStatisticsAvailable()
            updateState {
                copy(
                    isLoading = false,
                    isAvailableDeckStatistics = isDeckStatisticsAvailable,
                    isAvailableCardsStatistics = isCardStatisticsAvailable

                )
            }
        }
    }
}
