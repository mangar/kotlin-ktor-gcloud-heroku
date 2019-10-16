package main.kotlin.repository

import br.com.mng.panorama.model.AjusteFechamento
import br.com.mng.panorama.model.Resumo
import br.com.mng.panorama.model.ResumoFactory


class ResumoRepository(val db: AjustesFechamentosRepository) {

  suspend fun get(): Resumo {
    val all = db.all()

    val first = all.first()
    val second = if (all.size > 1) {
      all[1]
    } else AjusteFechamento()

    return ResumoFactory.create(first, second)
  }
}
