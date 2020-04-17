package co.nyenjes.agent.controller

import co.nyenjes.agent.model.InvoiceStatus
import co.nyenjes.agent.repository.InvoiceStatusRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/invoice_status")
class InvoiceStatusController(private val invoiceStatusRepository: InvoiceStatusRepository) {

    @GetMapping
    fun getAllInvoiceStatuss(): MutableList<InvoiceStatus> {
        val response = invoiceStatusRepository.findAllByOrderByIdAsc()
        logger.info { "getAllInvoiceStatuss : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getInvoiceStatusById(@PathVariable id: Long): ResponseEntity<InvoiceStatus> {
        val response = invoiceStatusRepository.findById(id).map { place ->
            ResponseEntity.ok(place)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getInvoiceStatusById : ${response}" }
        return response
    }

    @PostMapping
    fun createInvoiceStatus(@Valid @RequestBody request: InvoiceStatus): ResponseEntity<InvoiceStatus> {
        val jsonRequest = Gson().toJson(request)
        logger.info { "createInvoiceStatus : ${jsonRequest}" }
        val response = invoiceStatusRepository.save(request)
        logger.info { "createInvoiceStatus : ${response}" }
        return ResponseEntity.ok(response)
    }

    @PatchMapping("/{id}")
    fun updateInvoiceStatus(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<InvoiceStatus>? {
        val jsonRequest = Gson().toJson(request)
        logger.info { "updateInvoiceStatus : ${jsonRequest}" }
        val item = invoiceStatusRepository.findById(id)
        val updatedInvoiceStatus = item.get()
        val updatedInvoiceStatusJsonString = Gson().toJson(updatedInvoiceStatus, InvoiceStatus::class.java)
        val updatedInvoiceStatusEntity = Gson().fromJson(updatedInvoiceStatusJsonString, InvoiceStatus::class.java)

        if (request["nationalId"] != null) {
            updatedInvoiceStatusEntity.name = request["name"] as String
        }

        logger.info { "updateInvoiceStatus : ${updatedInvoiceStatusEntity}" }

        return ResponseEntity.ok().body(invoiceStatusRepository.save(updatedInvoiceStatusEntity))
    }

}