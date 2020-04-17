package co.nyenjes.agent.repository

import co.nyenjes.agent.model.Building
import co.nyenjes.agent.model.House
import org.springframework.data.jpa.repository.JpaRepository

interface HouseRepository: JpaRepository<House, Long> {
    fun findAllByOrderByIdAsc(): MutableList<House>

    fun findAllByBuildingId(buildingId: Building): MutableList<House>


}
