package co.nyenjes.agent.controller

import co.nyenjes.agent.model.HouseStatus
import co.nyenjes.agent.repository.HouseStatusRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/house_status")
class HouseStatusController(private val houseStatusRepository: HouseStatusRepository) {

    @GetMapping
    fun getAllHouseStatuss(): MutableList<HouseStatus> {
        val response = houseStatusRepository.findAllByOrderByIdAsc()
        logger.info { "getAllHouseStatuss : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getHouseStatusById(@PathVariable id: Long): ResponseEntity<HouseStatus> {
        val response = houseStatusRepository.findById(id).map { place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getHouseStatusById : ${response}" }
        return response
    }

    @PostMapping
    fun createHouseStatus(@Valid @RequestBody request: HouseStatus): ResponseEntity<HouseStatus> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createHouseStatus : ${jsonRequest}" }
        val response = houseStatusRepository.save(request)
        logger.info { "createHouseStatus : ${response}" }
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    fun updateHouseStatus(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<HouseStatus>? {
        val jsonRequest = Gson().toJson(request)
        logger.info { "updateHouseStatus : ${jsonRequest}" }
        val item = houseStatusRepository.findById(id)
        val updatedHouseStatus = item.get()
        val updatedHouseStatusJsonString = Gson().toJson(updatedHouseStatus, HouseStatus::class.java)
        val updatedHouseStatusEntity = Gson().fromJson(updatedHouseStatusJsonString, HouseStatus::class.java)

        if (request["nationalId"] != null) {
            updatedHouseStatusEntity.name = request["name"] as String
        }

        logger.info { "updateHouseStatus : ${updatedHouseStatusEntity}" }

        return ResponseEntity.ok().body(houseStatusRepository.save(updatedHouseStatusEntity))
    }

}
