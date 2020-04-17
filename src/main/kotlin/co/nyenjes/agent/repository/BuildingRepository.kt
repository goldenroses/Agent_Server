package co.nyenjes.agent.repository

import co.nyenjes.agent.model.Building
import org.springframework.data.jpa.repository.JpaRepository

interface BuildingRepository: JpaRepository<Building, Long> {
    fun findAllByOrderByIdAsc(): MutableList<Building>

}
