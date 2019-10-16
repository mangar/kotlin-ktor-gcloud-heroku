package main.kotlin.webapp

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route
import main.kotlin.repository.AjustesFechamentosRepository
import main.kotlin.repository.sheet.AjustesFechamentosSheet

const val LOADDB_PATH = "/loadDB"

@Location(LOADDB_PATH)
class LoadDBAction

fun Route.loadDB(db: AjustesFechamentosRepository, sheet: AjustesFechamentosSheet) {
  get<LoadDBAction> {
    db.loadFromSheet(sheet.listAll())
    call.respondText("Database Loaded")
  }
}
