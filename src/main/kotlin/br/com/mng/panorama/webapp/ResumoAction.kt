package main.kotlin.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import main.kotlin.repository.ResumoRepository
import java.util.*

const val RESUMO_PATH = "/resumo"

@Location(RESUMO_PATH)
class ResumoAction

fun Route.resumo(resumoRepo: ResumoRepository) {
  get<ResumoAction> {
    val resumo = resumoRepo.get()
    call.respond(FreeMarkerContent("resumo/resumo.ftl", mapOf("resumo" to resumo, "today" to Date())))
  }
}
