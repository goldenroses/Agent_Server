package co.nyenjes.agent.controller

import co.nyenjes.agent.model.Account
import co.nyenjes.agent.model.House
import co.nyenjes.agent.repository.AccountRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/account")
class AccountController(private val accountRepository: AccountRepository) {

    @GetMapping("/house/{id}")
    fun getAccountByHouseId(@PathVariable id: Long): MutableList<Account> {
        val house = House(id = id)
        val response = accountRepository.findByHouse(house)
        logger.info { "getAllAccounts : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: Long): ResponseEntity<Account> {
        val response = accountRepository.findById(id).map { place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getAccountById : ${response}" }
        return response
    }

    @PostMapping
    fun createAccount(@Valid @RequestBody request: Account): ResponseEntity<Account> {
        val response = accountRepository.save(request)
        logger.info { "createAccount : ${response}" }
        return ResponseEntity.ok(response)
    }

}
