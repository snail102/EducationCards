package ru.anydevprojects.educationcards.profile.domain

interface ProfileRepository {

    suspend fun isCardStatisticsAvailable(): Boolean

    suspend fun isDeckStatisticsAvailable(): Boolean
}
