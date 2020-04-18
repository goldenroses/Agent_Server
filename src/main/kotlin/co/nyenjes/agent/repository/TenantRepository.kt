package co.nyenjes.agent.repository

import co.nyenjes.agent.model.Tenant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface TenantRepository: JpaRepository<Tenant, Long> {
    fun findAllByOrderByIdAsc(): MutableList<Tenant>

    @Transactional
    @Query("select t.tenant_name, t.tenant_mobile from agent.house h inner join agent.tenant t on t.id=h.tenant where h.id=:houseId", nativeQuery = true)
    fun findTenantByHouseId(houseId: Long): List<Any>
}
