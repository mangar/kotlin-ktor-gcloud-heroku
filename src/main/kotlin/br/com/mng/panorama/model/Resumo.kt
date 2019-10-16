package br.com.mng.panorama.model

import java.text.SimpleDateFormat
import java.util.*

data class Resumo(val dataPregao: Date = Date(SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000").time),
                  val ajusteAnterior: Double = 0.0,
                  val ajuste: Double = 0.0,
                  val fechamento: Double = 0.0,
                  val variacao1Mais: Double = 0.0,
                  val variacao1Menos: Double = 0.0,
                  val indicesMundiais: PanoramaResult = PanoramaResult.NEUTRO,
                  val dx: PanoramaResult = PanoramaResult.NEUTRO,
                  val estrangeiros: PanoramaResult = PanoramaResult.NEUTRO,
                  val es: PanoramaResult = PanoramaResult.NEUTRO,
                  val cl: PanoramaResult = PanoramaResult.NEUTRO,
                  val noticias: PanoramaResult = PanoramaResult.NEUTRO,
                  val panorama: PanoramaResult = PanoramaResult.NEUTRO,
                  val estrangeirosPosicao: Int = 0,
                  val estrangeirosOperacoes: Int = 0)


enum class PanoramaResult(val count: Int, val display: String) {
  SUPER_POSITIVO(2, "++"),
  POSITIVO(1, "+"),
  NEUTRO(0, "+/-"),
  NEGATIVO(-1, "-"),
  SUPER_NEGATIVO(-2, "--")
}


object ResumoFactory {

  fun create(ajusteAtual: AjusteFechamento, ajusteAnterior: AjusteFechamento): Resumo =
    Resumo(
      dataPregao = ajusteAtual.data,
      ajusteAnterior = ajusteAnterior.ajuste,
      ajuste = ajusteAtual.ajuste,
      fechamento =  ajusteAtual.fechamento,
      variacao1Mais = ajusteAtual.variacao1PerMais,
      variacao1Menos = ajusteAtual.variacao1PerMenos,
      indicesMundiais = ajusteAtual.indicesMundiais,
      dx = ajusteAtual.dx,
      estrangeiros = ajusteAtual.estrangeiros,
      es = ajusteAtual.es,
      cl = ajusteAtual.cl,
      noticias = ajusteAtual.noticias,
      panorama = ajusteAtual.panorama,
      estrangeirosPosicao = ajusteAtual.estrangeirosPosicao,
      estrangeirosOperacoes = ajusteAtual.estrangeirosOperacoes
    )

}




