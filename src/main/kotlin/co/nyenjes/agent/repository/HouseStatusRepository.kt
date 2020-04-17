package co.nyenjes.agent.repository

import co.nyenjes.agent.model.HouseStatus
import org.springframework.data.jpa.repository.JpaRepository

interface HouseStatusRepository: JpaRepository<HouseStatus, Long> {
    fun findAllByOrderByIdAsc(): MutableList<HouseStatus>
}
