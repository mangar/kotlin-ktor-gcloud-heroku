package br.com.mng.panorama.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

const val HOME_PATH = "/"

@Location(HOME_PATH)
class HomeAction


fun Route.home() {
  get<HomeAction> {
    call.respond(FreeMarkerContent("home/index.ftl", null))
  }
}

