package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.repository.UserRepository
import javax.inject.Inject

class IsUserHasReachedFreeSearchLimitUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private val user by userRepository::user

    operator fun invoke(): Boolean {
        val currentDayInMillis = System.currentTimeMillis() / 86400000 * 86400000
        val currentUserTimeInMillis = user.firstRequestTimeInMillis
        val currentSearchCount = user.currentSearchCount

        return (currentSearchCount > 10 && currentDayInMillis < currentUserTimeInMillis)
    }

}