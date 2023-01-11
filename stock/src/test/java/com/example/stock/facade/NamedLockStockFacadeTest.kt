package com.example.stock.facade

import com.example.stock.domain.Stock
import com.example.stock.repository.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import java.util.concurrent.*

@SpringBootTest
class NamedLockStockFacadeTest @Autowired constructor(
  private val stockService: NamedLockStockFacade,
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

  @DisplayName("동시에_100개의_요청")
  @Test
  fun request_100() {
    val threadCount = 100
    val executorService: ExecutorService = Executors.newFixedThreadPool(100)
    val latch: CountDownLatch = CountDownLatch(threadCount) // 다른 스레드에서 수행중인 작업을 마무리할 때까지 기다리기 위해 사용

    for (i in 0 until threadCount) {
      executorService.submit{
        try {
          stockService.decrease(1L, 1L)
        } finally {
          latch.countDown()
        }
      }
    }

    latch.await()

    val stock: Stock = stockRepository.findByIdOrNull(1L) ?: throw RuntimeException("stock null error")

    assertEquals(0L, stock.quantity) // 100 - (1 * 100) = 0
  }
}