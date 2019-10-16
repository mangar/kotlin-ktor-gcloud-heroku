package main.kotlin.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import main.kotlin.repository.ReguaRepository

const val REGUA_PATH = "/regua"

@Location(REGUA_PATH)
class ReguaAction

fun Route.regua(repository: ReguaRepository) {
  get<ReguaAction> {
    val params = call.parameters
    val preco = params["i"] ?: "0"

    val precos = repository.reguaDePreco(preco.toInt())
    call.respond(FreeMarkerContent("regua/regua.ftl", mapOf("precos" to precos)))
  }
}
