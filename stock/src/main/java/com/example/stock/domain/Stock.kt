package com.example.stock.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

@Entity
class Stock (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long? = null,

  val productId: Long,

  var quantity: Long,

  @Version
  var version: Long? = null
) {

  fun decrease(quantity: Long) {
    if (this.quantity - quantity < 0) {
      throw RuntimeException("foo")
    }

    this.quantity = this.quantity - quantity
  }
}