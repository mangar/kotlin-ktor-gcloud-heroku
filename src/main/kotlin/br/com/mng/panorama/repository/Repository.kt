package main.kotlin.repository

interface Repository<T> {
  suspend fun all(): List<T>
  suspend fun first(): T
  suspend fun add(data: T)
  suspend fun clear()
  suspend fun remove(id: Int)
  suspend fun loadFromSheet(sheetData: List<T>)
}
