package co.nyenjes.agent.controller

import co.nyenjes.agent.model.Building
import co.nyenjes.agent.repository.BuildingRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/building")
class BuildingController(private val buildingRepository: BuildingRepository) {

    @GetMapping
    fun getAllBuildings(): MutableList<Building> {
        val response = buildingRepository.findAllByOrderByIdAsc()
        logger.info { "getAllBuildings : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getBuildingById(@PathVariable id: Long): ResponseEntity<Building> {
        val response = buildingRepository.findById(id).map { place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getBuildingById : ${response}" }
        return response
    }

    @PostMapping
    fun createBuilding(@Valid @RequestBody request: Building): ResponseEntity<Building> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createBuilding : ${jsonRequest}" }
        val response = buildingRepository.save(request)
        logger.info { "createBuilding : ${response}" }
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    fun updateBuilding(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<Building>? {
        val jsonRequest = Gson().toJson(request)
        logger.info { "updateBuilding : ${jsonRequest}" }
        val item = buildingRepository.findById(id)
        val updatedBuilding = item.get()
        val updatedBuildingJsonString = Gson().toJson(updatedBuilding, Building::class.java)
        val updatedBuildingEntity = Gson().fromJson(updatedBuildingJsonString, Building::class.java)

        if (request["buildingName"] != null) {
            updatedBuildingEntity.buildingName = request["buildingName"] as String
        }
        if (request["buildingDescription"] != null) {
            updatedBuildingEntity.buildingDescription = request["buildingDescription"] as String
        }
        if (request["buildingLocation"] != null) {
            updatedBuildingEntity.buildingLocation = request["buildingLocation"] as String
        }
        if (request["buildingNumberOfHouses"] != null) {
            updatedBuildingEntity.buildingNumberOfHouses = request["buildingNumberOfHouses"] as String
        }
        if (request["buildingOwner"] != null) {
            updatedBuildingEntity.buildingOwner = request["buildingOwner"] as String
        }
        if (request["buildingRegistrationDate"] != null) {
            updatedBuildingEntity.buildingRegistrationDate = request["buildingRegistrationDate"] as String
        }

        logger.info { "updateBuilding : ${updatedBuildingEntity}" }

        return ResponseEntity.ok().body(buildingRepository.save(updatedBuildingEntity))
    }

}
