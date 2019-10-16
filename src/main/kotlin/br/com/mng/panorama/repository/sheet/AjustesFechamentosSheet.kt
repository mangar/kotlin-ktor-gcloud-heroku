package main.kotlin.repository.sheet

import br.com.mng.panorama.model.AjusteFechamento


class AjustesFechamentosSheet :
  SheetDataSource("1rVY2z1YB2jMYGSe14hndBLmJg71xr7Z7WoMxf8hvSNo", "data_export!A:P") {

  fun listAll(): List<AjusteFechamento> =
    getAll.execute().getValues().toRawEntries()
      .mapIndexed { i, it -> it.toAjusteFechamento(i) }
}

private fun List<List<Any>>.toRawEntries(): List<Map<String, Any>> {
  val headers = first().map { it as String }
  val rows = drop(1)
  return rows.map { row -> headers.zip(row).toMap() }
}


private fun Map<String, Any>.toAjusteFechamento(i: Int) = AjusteFechamento(
  data = dateFor("data"),
  pregao = i,
  ajuste = doubleFor("ajuste"),
  fechamento = doubleFor("fechamento"),
  variacao1PerMais = if (i == 0) {
    Math.ceil(doubleFor("fechamento") * 1.01)
  } else {
    0.0
  },
  variacao1PerMenos = if (i == 0) {
    Math.ceil(doubleFor("fechamento") - (doubleFor("fechamento") * 0.01))
  } else {
    0.0
  },
  abertura = doubleFor("abertura"),
  minima = doubleFor("minima"),
  maxima = doubleFor("maxima"),
  estrangeirosOperacoes = intFor("estrangeirosOperacoes"),
  estrangeirosPosicao = intFor("estrangeirosPosicao"),
  aberturaPanorama = panoramaFor("aberturaPanorama"),
  estrangeiros = panoramaFor("estrangeiros"),
  indicesMundiais = panoramaFor("indicesMundiais"),
  dx = panoramaFor("dx"),
  es = panoramaFor("es"),
  cl = panoramaFor("cl"),
  noticias = panoramaFor("noticias"),
  panorama = panoramaFor("panorama"))
