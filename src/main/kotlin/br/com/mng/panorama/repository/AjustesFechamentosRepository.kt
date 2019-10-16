package main.kotlin.repository

import br.com.mng.panorama.model.AjusteFechamento
import br.com.mng.panorama.model.AjustesFechamentosDB
import br.com.mng.panorama.model.AjustesFechamentosDB.pregao
import br.com.mng.panorama.model.AjustesFechamentosDB.toAjusteFechamento
import main.kotlin.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime


class AjustesFechamentosRepository: Repository<AjusteFechamento> {

  override suspend fun all(): List<AjusteFechamento> =
    dbQuery {
      AjustesFechamentosDB.selectAll().orderBy(pregao).mapNotNull { toAjusteFechamento(it) }
    }


  override suspend fun first(): AjusteFechamento =
    dbQuery {
      AjustesFechamentosDB.selectAll().orderBy(pregao).mapNotNull { toAjusteFechamento(it) }.first()
    }

  override suspend fun add(ajusteParam: AjusteFechamento) {
    transaction {
      AjustesFechamentosDB.insert {
        it[dataPregao] = DateTime(ajusteParam.data)
        it[pregao] = ajusteParam.pregao
        it[ajuste] = ajusteParam.ajuste
        it[fechamento] = ajusteParam.fechamento
        it[variacao1PerMais] = ajusteParam.variacao1PerMais
        it[variacao1PerMenos] = ajusteParam.variacao1PerMenos
        it[abertura] = ajusteParam.abertura
        it[minima] = ajusteParam.minima
        it[maxima] = ajusteParam.maxima
        it[aberturaPanorama] = ajusteParam.aberturaPanorama.count
        it[indicesMundiais] = ajusteParam.indicesMundiais.count
        it[dx] = ajusteParam.dx.count
        it[estrangeiros] = ajusteParam.estrangeiros.count
        it[es] = ajusteParam.es.count
        it[cl] = ajusteParam.cl.count
        it[noticias] = ajusteParam.noticias.count
        it[panorama] = ajusteParam.panorama.count
        it[estrangeirosPosicao] = ajusteParam.estrangeirosPosicao
        it[estrangeirosOperacoes] = ajusteParam.estrangeirosOperacoes
      }
    }
  }

  override suspend fun clear() {
    transaction {
      AjustesFechamentosDB.deleteAll()
    }
  }

  override suspend fun remove(pregao: Int) {
    transaction {
      AjustesFechamentosDB.deleteWhere { AjustesFechamentosDB.pregao.eq(pregao) }
    }
  }

  override suspend fun loadFromSheet(sheetData: List<AjusteFechamento>) {
    clear()
    sheetData.map { add(it) }
  }


}
