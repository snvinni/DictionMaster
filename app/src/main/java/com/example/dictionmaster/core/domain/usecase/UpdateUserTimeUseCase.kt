package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserTimeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private val user by userRepository::user
    suspend operator fun invoke() {
        val currentUserTimeInMillis = user.firstRequestTimeInMillis
        val currentDayInMillis = System.currentTimeMillis() / 86400000 * 86400000
        val hasDayChanged = currentUserTimeInMillis < currentDayInMillis

        if (!hasDayChanged) return

        userRepository.updateLocally {
            it.copy(
                currentSearchCount = 0,
                firstRequestTimeInMillis = System.currentTimeMillis()
            )
        }
   }
}