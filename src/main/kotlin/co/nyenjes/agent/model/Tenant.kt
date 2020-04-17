package co.nyenjes.agent.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tenant")
class Tenant(
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,

    @JsonProperty("tenant_name")
    @Column(name = "tenant_name")
    var tenantName: String? = null,

    @JsonProperty("tenant_mobile")
    @Column(name = "tenant_mobile")
    var tenantMobile: String? = null,

    @JsonProperty("tenant_national_id")
    @Column(name = "tenant_national_id")
    var tenantNationalId: String? = null,

    @JsonProperty("tenant_agreement")
    @Column(name = "tenant_agreement")
    var tenantAgreement: String? = null,

    @JsonProperty("tenant_email")
    @Column(name = "tenant_email")
    var tenantEmail: String? = null
)
