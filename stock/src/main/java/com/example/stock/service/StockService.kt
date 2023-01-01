package com.example.stock.service

import com.example.stock.domain.Stock
import com.example.stock.repository.StockRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StockService(
  private val stockRepository: StockRepository
) {

  fun decrease(id: Long, quantity: Long) {
    val stock: Stock = stockRepository.findByIdOrNull(id) ?: throw RuntimeException("stock null error")

    stock.decrease(quantity)

    stockRepository.saveAndFlush(stock)
  }

}