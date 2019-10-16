package main.kotlin.repository

import br.com.mng.panorama.model.AjustesFechamentosDB
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

  fun init() {
    Database.Companion.connect(hikari())

    transaction {
      SchemaUtils.create(AjustesFechamentosDB)
      SchemaUtils.createMissingTablesAndColumns(AjustesFechamentosDB)
    }
  }

  /**
   * TODO load configuration from a config file
   */
  private fun hikari(): HikariDataSource {
    val config = HikariConfig()
    config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
    config.maximumPoolSize = 3
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    config.validate()
    return HikariDataSource(config)
  }


  suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
      transaction { block() }
    }

}
