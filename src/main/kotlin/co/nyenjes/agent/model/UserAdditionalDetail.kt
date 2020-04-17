package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user_additional_details")
data class UserAdditionalDetail (
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id : Long = 0,

    @JsonProperty("first_name")
    @Column(name ="first_name")
    var firstName: String? = null,

    @JsonProperty("last_name")
    @Column(name = "last_name")
    var lastName: String? = null,

    @JsonProperty("profile_image_url")
    @Column(name = "profile_image_url")
    var profileImageUrl: String? = null,

    @JsonProperty("email")
    @Column(name = "email")
    var email: String? = null,

    @JsonProperty("phone_number")
    @Column(name = "phone_number")
    var phoneNumber: Long? = null,

    @JsonProperty("residential_address")
    @Column(name = "residential_address")
    var residentialAddress: String? = null
) : Serializable
