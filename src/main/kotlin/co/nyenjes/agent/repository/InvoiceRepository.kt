package co.nyenjes.agent.repository

import co.nyenjes.agent.model.House
import co.nyenjes.agent.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository: JpaRepository<Invoice, Long> {
    fun findAllByHouse(house: House): MutableList<Invoice>

}
