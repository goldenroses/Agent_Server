//package co.nyenjes.agent.model
//
//import com.fasterxml.jackson.annotation.JsonBackReference
//import com.fasterxml.jackson.annotation.JsonIgnore
//import com.fasterxml.jackson.annotation.JsonProperty
//import javax.persistence.*
//import javax.validation.constraints.NotBlank
//
//@Entity
//@Table(name = "account")
//class Account(
//    @JsonProperty("id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id @NotBlank @Column(name = "id") var id: Long = 0,
//
//    @JsonProperty("house")
//    @JoinColumn(name = "house")
//    @OneToOne
//    var house: House? = null,
//
//    @JsonProperty("invoice")
//    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
//    @JsonIgnore
//    var invoice: Set<Invoice>? = HashSet<Invoice>(),
//
//    @JsonProperty("receipt")
//    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
//    @JsonIgnore
//    var receipt: Set<Receipt>? = HashSet<Receipt>()
//
//)
