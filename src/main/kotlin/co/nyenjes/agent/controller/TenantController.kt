package co.nyenjes.agent.controller

import co.nyenjes.agent.model.Tenant
import co.nyenjes.agent.repository.TenantRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/tenant")
class TenantController(private val tenantRepository: TenantRepository) {

    @GetMapping
    fun getAllTenants(): MutableList<Tenant> {
        val response = tenantRepository.findAllByOrderByIdAsc()
        logger.info { "getAllTenants : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getTenantById(@PathVariable id: Long): ResponseEntity<Tenant> {
        val response = tenantRepository.findById(id).map { place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getTenantById : ${response}" }
        return response
    }

    @PostMapping
    fun createTenant(@Valid @RequestBody request: Tenant): ResponseEntity<Tenant> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createTenant : ${jsonRequest}" }
        val response = tenantRepository.save(request)
        logger.info { "createTenant : ${response}" }
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    fun updateTenant(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<Tenant>? {
        val jsonRequest = Gson().toJson(request)
        logger.info { "updateTenant : ${jsonRequest}" }
        val item = tenantRepository.findById(id)
        val updatedTenant = item.get()
        val updatedTenantJsonString = Gson().toJson(updatedTenant, Tenant::class.java)
        val updatedTenantEntity = Gson().fromJson(updatedTenantJsonString, Tenant::class.java)

        if (request["nationalId"] != null) {
            updatedTenantEntity.tenantName = request["tenantName"] as String
        }
        if (request["kraPin"] != null) {
            updatedTenantEntity.tenantMobile = request["tenantDescription"] as String
        }
        if (request["policeClearance"] != null) {
            updatedTenantEntity.tenantNationalId = request["tenantLocation"] as String
        }
        if (request["processingCertificate"] != null) {
            updatedTenantEntity.tenantAgreement = request["tenantNumberOfHouses"] as String
        }
        if (request["passportPhoto"] != null) {
            updatedTenantEntity.tenantEmail = request["tenantOwner"] as String
        }

        logger.info { "updateTenant : ${updatedTenantEntity}" }

        return ResponseEntity.ok().body(tenantRepository.save(updatedTenantEntity))
    }

}