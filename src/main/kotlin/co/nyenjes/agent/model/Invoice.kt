package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "invoice")
class Invoice(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @JsonProperty("house")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "house")
    var house: House? = null,

    @JsonProperty("invoice_name")
    @Column(name = "invoice_name")
    var invoiceName: String? = null,

    @JsonProperty("invoice_number")
    @Column(name = "invoice_number")
    var invoiceNumber: String? = null,

    @JsonProperty("invoice_amount")
    @Column(name = "invoice_amount")
    var invoiceAmount: String? = null,

    @JsonProperty("status")
    @OneToOne
    @JoinColumn(name = "status")
    var status: InvoiceStatus? = null,

    @JsonProperty("water_payment")
    @Column(name = "water_payment")
    var waterPayment: String? = null,

    @JsonProperty("security_payment")
    @Column(name = "security_payment")
    var securityPayment: String? = null,

    @JsonProperty("amenities_payment")
    @Column(name = "amenities_payment")
    var amenitiesPayment: String? = null
)
