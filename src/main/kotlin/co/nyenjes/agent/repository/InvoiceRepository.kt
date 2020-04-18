package co.nyenjes.agent.repository

import co.nyenjes.agent.model.House
import co.nyenjes.agent.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface InvoiceRepository: JpaRepository<Invoice, Long> {
    fun findAllByHouse(house: House): MutableList<Invoice>

    @Transactional
    @Query("select b.building_name, h.id as hid, i.id as iId, h.house_name as hN,b.id as bId, i.house, i.invoice_number from agent.invoice i inner join agent.house h on i.house=h.id inner join agent.building b on h.building_id=b.id where b.id=:buildingId", nativeQuery = true)
    fun findAllInvoicesByBuildingId(buildingId: Long): List<Any>
}
