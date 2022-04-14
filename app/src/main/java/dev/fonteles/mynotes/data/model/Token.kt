package dev.fonteles.mynotes.data.model

data class Token (val username: String, val hash: String, val creationDate: String = "") {
    override fun toString(): String {
        return "Token: $username, $hash, $creationDate"
    }
}