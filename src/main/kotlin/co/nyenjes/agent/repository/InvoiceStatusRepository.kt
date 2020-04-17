package co.nyenjes.agent.repository

import co.nyenjes.agent.model.InvoiceStatus
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceStatusRepository: JpaRepository<InvoiceStatus, Long> {
    fun findAllByOrderByIdAsc(): MutableList<InvoiceStatus>
}
