package com.example.dictionmaster.core.domain.usecase

import com.example.dictionmaster.core.domain.repository.UserRepository
import com.example.dictionmaster.Constants
import javax.inject.Inject

class IsUserHasReachedFreeSearchLimitUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    private val user by userRepository::user

    operator fun invoke(): Boolean {
        val currentDayInMillis = System.currentTimeMillis() /
                Constants.DAY_IN_MILLIS * Constants.DAY_IN_MILLIS
        val currentUserTimeInMillis = user.firstRequestTimeInMillis
        val currentSearchCount = user.currentSearchCount

        return (
                currentSearchCount > Constants.DAILY_LIMIT_OF_SEARCHES &&
                        currentDayInMillis < currentUserTimeInMillis
                )
    }

}