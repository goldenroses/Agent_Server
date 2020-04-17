package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "house")
class House(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @JsonProperty("building_id")
    @OneToOne
    @JoinColumn(name = "building_id")
    var buildingId: Building? = null,

    @JsonProperty("house_name")
    @Column(name = "house_name")
    var houseName: String? = null,

    @JsonProperty("house_floor_number")
    @Column(name = "house_floor_number")
    var houseFloorNumber: String? = null,

    @JsonProperty("house_description")
    @Column(name = "house_description")
    var houseDescription: String? = null,

    @JsonProperty("status")
    @OneToOne
    @JoinColumn(name = "status")
    var status: HouseStatus? = null
)
