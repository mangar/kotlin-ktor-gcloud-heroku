package br.com.mng.panorama.model

import io.ktor.auth.Principal

data class User(val username: String) : Principal
