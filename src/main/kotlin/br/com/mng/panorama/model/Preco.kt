package br.com.mng.panorama.model


data class DataPointSheet(val pregao: Int = 99,
                          val ajuste: Double = 0.0,
                          val fechamento: Double = 0.0,
                          val variacao1PerMais: Double = 0.0,
                          val variacao1PerMenos: Double = 0.0,
                          val abertura : Double = 0.0,
                          val minima: Double = 0.0,
                          val maxima: Double = 0.0) : java.io.Serializable


data class DataPoint(val pregao: Int, val preco: Double = 0.0, val tipo: DataPointType) : java.io.Serializable


enum class DataPointType(val code: String) {
  AJUSTE("AJ"),
  FECHAMENTO("FE"),
  ABERTURA("AB"),
  MINIMA("MIN"),
  MAXIMA("MAX"),
  VAR1MAIS("+1%"),
  VAR1MENOS("-1%")
}


data class Preco(val preco: Double = 0.0,
                 var dataPoints: List<DataPoint> = listOf()) {

  override fun toString(): String = "preco:$preco - dataPoints:$dataPoints"
}
