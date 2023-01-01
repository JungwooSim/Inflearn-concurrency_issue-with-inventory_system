package com.example.stock.service

import com.example.stock.domain.Stock
import com.example.stock.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class StockServiceTest @Autowired constructor(
  private val stockService: StockService,
  private val stockRepository: StockRepository
) {

  @BeforeEach
  fun before() {
    val stock = Stock(productId = 1L, quantity = 100L)

    stockRepository.saveAndFlush(stock)
  }

  @AfterEach
  fun after() {
    stockRepository.deleteAll()
  }

  @Test
  fun stock_decrease() {
    stockService.decrease(id = 1L, quantity = 1L)

    val stock = stockRepository.findByIdOrNull(1L) ?: throw RuntimeException("stock null error")

    assertEquals(99, stock.quantity)
  }
}