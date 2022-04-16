package dev.fonteles.mynotes.ui.login

data class LoginFormState (
    var usernameError: Int? = null,
    var passwordError: Int? = null
) {
    fun isValid(): Boolean {
        return (usernameError == null) && (passwordError == null)
    }
}