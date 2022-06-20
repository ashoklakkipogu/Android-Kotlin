package com.cba.transactions.utils

class RegistrationUtils {
    companion object {
        fun validateRegistrationInput(
            username: String,
            password: String,
            confirmedPassword: String
        ): Boolean {
            when {
                username.isEmpty() -> {
                    return false
                }
                password.isEmpty() -> {
                    return false
                }
                confirmedPassword.isEmpty() -> {
                    return false
                }
                password != confirmedPassword -> {
                    return false
                }
                password.length < 2 -> {
                    return false
                }
                else -> return true
            }
        }
    }

}