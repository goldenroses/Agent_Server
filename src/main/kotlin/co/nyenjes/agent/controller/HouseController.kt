package co.nyenjes.agent.controller

import co.nyenjes.agent.model.*
import co.nyenjes.agent.repository.HouseRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/house")
class HouseController(private val houseRepository: HouseRepository) {

    @GetMapping
    fun getAllHouses(): MutableList<House> {
        val response = houseRepository.findAllByOrderByIdAsc()
        logger.info { "getAllHouses : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getHouseById(@PathVariable id: Long): ResponseEntity<House> {
        val response = houseRepository.findById(id).map { place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getHouseById : ${response}" }
        return response
    }

    @GetMapping("building/{id}")
    fun getHouseByBuildingId(@PathVariable id: Long): MutableList<House> {
        val buildingId = Building(id = id)
        val response = houseRepository.findAllByBuildingId(buildingId)

        logger.info { "getHouseById : ${response}" }
        return response
    }

    @PostMapping
    fun createHouse(@Valid @RequestBody request: House): ResponseEntity<House> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createHouse : ${jsonRequest}" }
        val response = houseRepository.save(request)
        logger.info { "createHouse : ${response}" }
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/updateTenant/{houseId}")
    fun addTenantToHouse(@Valid @RequestBody request: Map<String, Any>, @PathVariable houseId: Long): ResponseEntity<House>? {
        val jsonRequest = Gson().toJson(request)
        logger.info { "updateHouse : ${jsonRequest}" }
        val item = houseRepository.findById(houseId)
        val updatedHouse = item.get()
        val updatedHouseJsonString = Gson().toJson(updatedHouse, House::class.java)
        val updatedHouseEntity = Gson().fromJson(updatedHouseJsonString, House::class.java)

        if (request["tenantId"] != null) {
            val tenant = Tenant(id = request["tenantId"].toString().toLong())
            updatedHouseEntity.tenant = tenant
        }

        logger.info { "updateHouse : ${updatedHouseEntity}" }

        return ResponseEntity.ok().body(houseRepository.save(updatedHouseEntity))
    }

    @PatchMapping("/{id}")
    fun updateHouse(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<House>? {
        val jsonRequest = Gson().toJson(request)
        logger.info { "updateHouse : ${jsonRequest}" }
        val item = houseRepository.findById(id)
        val updatedHouse = item.get()
        val updatedHouseJsonString = Gson().toJson(updatedHouse, House::class.java)
        val updatedHouseEntity = Gson().fromJson(updatedHouseJsonString, House::class.java)

        if (request["nationalId"] != null) {
            updatedHouseEntity.houseName = request["houseName"] as String
        }
        if (request["kraPin"] != null) {
            updatedHouseEntity.houseDescription = request["houseDescription"] as String
        }
        if (request["policeClearance"] != null) {
            updatedHouseEntity.houseFloorNumber = request["houseLocation"] as String
        }
        if (request["processingCertificate"] != null) {
            updatedHouseEntity.status = request["status"] as HouseStatus
        }

        logger.info { "updateHouse : ${updatedHouseEntity}" }

        return ResponseEntity.ok().body(houseRepository.save(updatedHouseEntity))
    }

}
