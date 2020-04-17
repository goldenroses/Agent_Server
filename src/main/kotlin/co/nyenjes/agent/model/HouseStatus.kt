package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "house_status")
class HouseStatus(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @JsonProperty("name")
    @Column(name = "name")
    var name: String? = null

)
