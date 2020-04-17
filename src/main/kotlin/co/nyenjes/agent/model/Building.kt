package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "building")
class Building(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @JsonProperty("building_name")
    @Column(name = "building_name")
    var buildingName: String? = null,

    @JsonProperty("building_location")
    @Column(name = "building_location")
    var buildingLocation: String? = null,

    @JsonProperty("building_description")
    @Column(name = "building_description")
    var buildingDescription: String? = null,

    @JsonProperty("building_owner")
    @Column(name = "building_owner")
    var buildingOwner: String? = null,

    @JsonProperty("building_number_of_houses")
    @Column(name = "building_number_of_houses")
    var buildingNumberOfHouses: String? = null,

    @JsonProperty("building_registration_date")
    @Column(name = "building_registration_date")
    var buildingRegistrationDate: String? = null
)
