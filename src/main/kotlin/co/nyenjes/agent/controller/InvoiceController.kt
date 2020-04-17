package co.nyenjes.agent.controller

import co.nyenjes.agent.model.House
import co.nyenjes.agent.model.Invoice
import co.nyenjes.agent.repository.InvoiceRepository
import com.google.gson.Gson
import mu.KotlinLogging
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/invoice")
class InvoiceController(private val invoiceRepository: InvoiceRepository) {

    @GetMapping("/house/{id}")
    fun getAllInvoicesByHouse(@PathVariable id: Long): MutableList<Invoice> {
        val house = House(id = id)
        val response = invoiceRepository.findAllByHouse(house)
        logger.info { "getAllInvoices : ${response}" }
        return response
    }

    @GetMapping("/{id}")
    fun getInvoiceById(@PathVariable id: Long): ResponseEntity<Invoice> {
        val response = invoiceRepository.findById(id).map { invoice ->
            ResponseEntity.ok(invoice)
        }.orElse(ResponseEntity.notFound().build())

        logger.info { "getInvoiceById : ${response}" }
        return response
    }

    @PostMapping("/house/{id}")
    fun createInvoice(@Valid @RequestBody request: Invoice): ResponseEntity<Invoice> {
        val response = invoiceRepository.save(request)
        logger.info { "createInvoice : ${response}" }
        return ResponseEntity.ok(response)
    }

//    @PatchMapping("/{id}")
//    fun updateInvoice(@Valid @RequestBody request: Map<String, Any>, @PathVariable id: Long): ResponseEntity<Invoice>? {
//        val jsonRequest = Gson().toJson(request)
//        logger.info { "updateInvoice : ${jsonRequest}" }
//        val item = invoiceRepository.findById(id)
//        val updatedInvoice = item.get()
//        val updatedInvoiceJsonString = Gson().toJson(updatedInvoice, Invoice::class.java)
//        val updatedInvoiceEntity = Gson().fromJson(updatedInvoiceJsonString, Invoice::class.java)
//
//        if (request["nationalId"] != null) {
//            updatedInvoiceEntity.invoiceName = request["invoiceName"] as String
//        }
//        if (request["kraPin"] != null) {
//            updatedInvoiceEntity.invoiceDescription = request["invoiceDescription"] as String
//        }
//        if (request["policeClearance"] != null) {
//            updatedInvoiceEntity.invoiceLocation = request["invoiceLocation"] as String
//        }
//        if (request["processingCertificate"] != null) {
//            updatedInvoiceEntity.invoiceNumberOfHouses = request["invoiceNumberOfHouses"] as String
//        }
//        if (request["passportPhoto"] != null) {
//            updatedInvoiceEntity.invoiceOwner = request["invoiceOwner"] as String
//        }
//        if (request["modeOfTransport"] != null) {
//            updatedInvoiceEntity.invoiceRegistrationDate = request["invoiceRegistrationDate"] as String
//        }
//
//        logger.info { "updateInvoice : ${updatedInvoiceEntity}" }
//
//        return ResponseEntity.ok().body(invoiceRepository.save(updatedInvoiceEntity))
//    }

}
