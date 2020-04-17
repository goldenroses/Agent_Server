package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "receipt")
class Receipt(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @JsonProperty("house")
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "house")
    var house: House? = null,

    @JsonProperty("receipt_number")
    @JoinColumn(name = "receipt_number")
    var receipt_number: String? = null,

    @JsonProperty("receipt_name")
    @Column(name = "receipt_name")
    var receiptName: String? = null,

    @JsonProperty("receipt_date")
    @Column(name = "receipt_date")
    var receiptDate: String? = null
)
