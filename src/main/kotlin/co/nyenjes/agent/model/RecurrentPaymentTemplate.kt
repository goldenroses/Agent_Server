package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "recurrent_payment_template")
class RecurrentPaymentTemplate(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

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
