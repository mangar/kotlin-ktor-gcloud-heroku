package org.jetbrains.ktor.heroku

import br.com.mng.panorama.webapp.home
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.install
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import main.kotlin.repository.AjustesFechamentosRepository
import main.kotlin.repository.DatabaseFactory
import main.kotlin.repository.ReguaRepository
import main.kotlin.repository.ResumoRepository
import main.kotlin.repository.sheet.AjustesFechamentosSheet
import main.kotlin.webapp.loadDB
import main.kotlin.webapp.regua
import main.kotlin.webapp.resumo
import org.slf4j.LoggerFactory


fun main(args: Array<String>) {
  val port = Integer.valueOf(System.getenv("PORT"))
  embeddedServer(Netty, port) {

    install(DefaultHeaders)

    install(FreeMarker) {
      templateLoader = ClassTemplateLoader(this::class.java.classLoader, "webapp")
    }

    install(Locations)

    DatabaseFactory.init()

    routing {
      static("/static") {
        resources("public")
      }

      val dbAjustesFechamentos = AjustesFechamentosRepository()
      val sheet = AjustesFechamentosSheet()
      val repoResumo = ResumoRepository(dbAjustesFechamentos)
      val repoRegua = ReguaRepository(dbAjustesFechamentos)

      home()
      loadDB(dbAjustesFechamentos, sheet)
      resumo(repoResumo)
      regua(repoRegua)
    }


  }.start(wait = true)
}


object App {

  val log = LoggerFactory.getLogger("Application");
  fun logInfo(message: String, group: String = "main") {
    log.info("[ $group ] $message");
  }

  fun logInfoA(message: String, group: String = "main") {
    log.info("[ $group ] >> $message");
  }

}

