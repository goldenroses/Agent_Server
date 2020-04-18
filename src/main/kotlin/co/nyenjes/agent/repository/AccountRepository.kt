//package co.nyenjes.agent.repository
//
//import co.nyenjes.agent.model.Account
//import co.nyenjes.agent.model.House
//import org.springframework.data.jpa.repository.JpaRepository
//
//interface AccountRepository: JpaRepository<Account, Long> {
//    fun findAllByOrderByIdAsc(): MutableList<Account>
//
//    fun findAllByHouse(house: House): MutableList<Account>
//
//
//}
