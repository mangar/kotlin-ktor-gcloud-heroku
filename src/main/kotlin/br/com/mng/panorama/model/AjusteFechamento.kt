package br.com.mng.panorama.model

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import java.text.SimpleDateFormat
import java.util.*

data class AjusteFechamento(
  val data: Date = Date(SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000").time),
  val id: Int = 99,
  val pregao: Int = 99,
  val ajuste: Double = 0.0,
  val fechamento: Double = 0.0,
  val variacao1PerMais: Double = 0.0,
  val variacao1PerMenos: Double = 0.0,
  val abertura: Double = 0.0,
  val minima: Double = 0.0,
  val maxima: Double = 0.0,
  val estrangeirosOperacoes: Int = 0,
  val estrangeirosPosicao: Int = 0,
  val aberturaPanorama: PanoramaResult = PanoramaResult.NEUTRO,
  val estrangeiros: PanoramaResult = PanoramaResult.NEUTRO,
  val indicesMundiais: PanoramaResult = PanoramaResult.NEUTRO,
  val dx: PanoramaResult = PanoramaResult.NEUTRO,
  val es: PanoramaResult = PanoramaResult.NEUTRO,
  val cl: PanoramaResult = PanoramaResult.NEUTRO,
  val noticias: PanoramaResult = PanoramaResult.NEUTRO,
  val panorama: PanoramaResult = PanoramaResult.NEUTRO) {
}

object AjustesFechamentosDB : IntIdTable() {
  val dataPregao = date("data").nullable()
  val pregao = integer("pregao")
  val ajuste = double("ajuste")
  val fechamento = double("fechamento")
  val variacao1PerMais = double("variacao1PerMais")
  val variacao1PerMenos = double("variacao1PerMenos")
  val abertura = double("abertura")
  val minima = double("minima")
  val maxima = double("maxima")
  val estrangeirosOperacoes = integer("estrangeirosOperacoes")
  val estrangeirosPosicao = integer("estrangeirosPosicao")
  val aberturaPanorama = integer("aberturaPanorama")
  val estrangeiros = integer("estrangeiros")
  val indicesMundiais = integer("indicesMundiais")
  val dx = integer("dx")
  val es = integer("es")
  val cl = integer("cl")
  val noticias = integer("noticias")
  val panorama = integer("panorama")

  fun toAjusteFechamento(row: ResultRow): AjusteFechamento =
    AjusteFechamento(
      data = if (row[AjustesFechamentosDB.dataPregao] != null) {
        row[AjustesFechamentosDB.dataPregao]?.toDate()!!
      } else {
        Date(SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000").time)
      },
      id = row[AjustesFechamentosDB.id].value,
      pregao = row[AjustesFechamentosDB.pregao],
      ajuste = row[AjustesFechamentosDB.ajuste],
      fechamento = row[AjustesFechamentosDB.fechamento],
      variacao1PerMais = row[AjustesFechamentosDB.variacao1PerMais],
      variacao1PerMenos = row[AjustesFechamentosDB.variacao1PerMenos],
      abertura = row[AjustesFechamentosDB.abertura],
      minima = row[AjustesFechamentosDB.minima],
      maxima = row[AjustesFechamentosDB.maxima],
      estrangeirosOperacoes = row[AjustesFechamentosDB.estrangeirosOperacoes],
      estrangeirosPosicao = row[AjustesFechamentosDB.estrangeirosPosicao],
      aberturaPanorama = panoramaResult(row[AjustesFechamentosDB.aberturaPanorama]),
      estrangeiros = panoramaResult(row[AjustesFechamentosDB.estrangeiros]),
      indicesMundiais = panoramaResult(row[AjustesFechamentosDB.indicesMundiais]),
      dx = panoramaResult(row[AjustesFechamentosDB.dx]),
      es = panoramaResult(row[AjustesFechamentosDB.es]),
      cl = panoramaResult(row[AjustesFechamentosDB.cl]),
      noticias = panoramaResult(row[AjustesFechamentosDB.noticias]),
      panorama = panoramaResult(row[AjustesFechamentosDB.panorama])
    )

  private fun panoramaResult(i: Int): PanoramaResult =
    when (i) {
      2 -> PanoramaResult.SUPER_POSITIVO
      1 -> PanoramaResult.POSITIVO
      0 -> PanoramaResult.NEUTRO
      -1 -> PanoramaResult.NEGATIVO
      -2 -> PanoramaResult.SUPER_NEGATIVO
      else -> PanoramaResult.NEUTRO

    }


}
