package main.kotlin.repository.sheet

import br.com.mng.panorama.model.PanoramaResult
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.Sheets
import main.kotlin.AppCredentials
import java.text.SimpleDateFormat
import java.util.*

open class SheetDataSource(val spreadsheet: String, val range: String) {

  private val transport = GoogleNetHttpTransport.newTrustedTransport()
  private val jacksonFactory = JacksonFactory.getDefaultInstance()
  val sheets: Sheets = Sheets.Builder(transport, jacksonFactory, AppCredentials.local).build()
  val getAll = sheets.spreadsheets().values().get(this.spreadsheet, this.range)!!

}


fun Map<String, Any>.stringFor(key: String) = (this[key] as? String) ?: ""
fun Map<String, Any>.intFor(key: String) = stringFor(key).toIntOrNull() ?: -1
fun Map<String, Any>.doubleFor(key: String) = stringFor(key).replace(",", ".").toDoubleOrNull() ?: -1.0
fun Map<String, Any>.dateFor(key: String): Date = Date(SimpleDateFormat("dd/MM/yyyy").parse(stringFor(key)).time)


fun Map<String, Any>.panoramaFor(key: String): PanoramaResult {
  val value = (this[key] as? String) ?: "0"
  return when (value) {
    "2" -> PanoramaResult.SUPER_POSITIVO
    "++" -> PanoramaResult.SUPER_POSITIVO
    "1" -> PanoramaResult.POSITIVO
    "+" -> PanoramaResult.POSITIVO
    "0" -> PanoramaResult.NEUTRO
    "+/-" -> PanoramaResult.NEUTRO
    "-1" -> PanoramaResult.NEGATIVO
    "-" -> PanoramaResult.NEGATIVO
    "-2" -> PanoramaResult.SUPER_NEGATIVO
    "--" -> PanoramaResult.SUPER_NEGATIVO
    else -> PanoramaResult.NEUTRO
  }
}
