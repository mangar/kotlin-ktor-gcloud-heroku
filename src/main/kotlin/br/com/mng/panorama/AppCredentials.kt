package main.kotlin

import com.google.api.client.http.HttpRequestInitializer
import com.google.api.services.sheets.v4.SheetsScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials

object AppCredentials {
  val local: HttpRequestInitializer by lazy {
    HttpCredentialsAdapter(GoogleCredentials.fromStream(pathStream).createScoped(scopes))
  }

  private val pathStream
    get() = AppCredentials::class.java.getResourceAsStream(credentialsFilePath)
      ?: error("Resource not found: $credentialsFilePath")

  private val scopes = listOf(SheetsScopes.SPREADSHEETS)

}

private const val credentialsFilePath = "/credential.json"


