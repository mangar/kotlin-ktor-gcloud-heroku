package main.kotlin.repository


import br.com.mng.panorama.model.*
import main.kotlin.repository.sheet.doubleFor

import java.lang.Math.ceil


class ReguaRepository(val db: AjustesFechamentosRepository) {


  suspend fun reguaDePreco(precoMedio: Int): List<Preco> {
    val first = db.first()
    val newPrecoMedio = if (precoMedio == 0) {
      first.fechamento.toInt()
    } else {
      precoMedio.toInt()
    }

    val newRegua = generateReguaDePrecos(newPrecoMedio - 50.0, newPrecoMedio + 50.0)
      .mergePrecosAndPoints(db.all())
    return newRegua
  }

  private fun generateReguaDePrecos(precoInicial: Double, precoFinal: Double): List<Preco> {
    val regua = mutableListOf<Preco>()
    (precoFinal.toInt().downTo(precoInicial.toInt())).map {
      regua.add(Preco((it + 0.5).toDouble()))
      regua.add(Preco(it.toDouble()))
    }
    return regua
  }


  private fun List<Preco>.mergePrecosAndPoints(points: List<AjusteFechamento>): List<Preco> =
    map {
      it.dataPoints = points.findEntryByPrice(it.preco)
      it
    }

}


private fun List<List<Any>>.toRawEntries(): List<Map<String, Any>> {
  val headers = first().map { it as String }
  val rows = drop(1)
  return rows.map { row -> headers.zip(row).toMap() }
}


private fun Map<String, Any>.toPoints(i: Int) = DataPointSheet(
  pregao = i,
  ajuste = doubleFor("ajuste"),
  fechamento = doubleFor("fechamento"),
  variacao1PerMais = if (i == 0) {
    ceil(doubleFor("fechamento") * 1.01)
  } else {
    0.0
  },
  variacao1PerMenos = if (i == 0) {
    ceil(doubleFor("fechamento") - (doubleFor("fechamento") * 0.01))
  } else {
    0.0
  },
  abertura = doubleFor("abertura"),
  minima = doubleFor("minima"),
  maxima = doubleFor("maxima")
)


fun List<AjusteFechamento>.findEntryByPrice(price: Double): List<DataPoint> =
  mapNotNull {
    when (price) {
      it.abertura -> DataPoint(it.pregao, price, DataPointType.ABERTURA)
      it.ajuste -> DataPoint(it.pregao, price, DataPointType.AJUSTE)
      it.fechamento -> DataPoint(it.pregao, price, DataPointType.FECHAMENTO)
      it.variacao1PerMais -> DataPoint(it.pregao, price, DataPointType.VAR1MAIS)
      it.variacao1PerMenos -> DataPoint(it.pregao, price, DataPointType.VAR1MENOS)
      else -> null
    }
  }
