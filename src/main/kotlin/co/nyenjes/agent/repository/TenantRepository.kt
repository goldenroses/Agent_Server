package co.nyenjes.agent.repository

import co.nyenjes.agent.model.Tenant
import org.springframework.data.jpa.repository.JpaRepository

interface TenantRepository: JpaRepository<Tenant, Long> {
    fun findAllByOrderByIdAsc(): MutableList<Tenant>

}
