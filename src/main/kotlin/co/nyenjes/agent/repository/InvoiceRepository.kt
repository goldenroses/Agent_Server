package co.nyenjes.agent.repository

import co.nyenjes.agent.model.Account
import co.nyenjes.agent.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository: JpaRepository<Invoice, Long> {
    fun findAllByAccount(account: Account): MutableList<Invoice>

}
