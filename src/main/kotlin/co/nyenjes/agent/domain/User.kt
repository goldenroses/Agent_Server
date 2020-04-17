package co.nyenjes.agent.domain

import co.nyenjes.agent.annotation.NoArg
import co.nyenjes.agent.model.UserAdditionalDetail
import com.fasterxml.jackson.annotation.JsonIgnore
import org.codehaus.jackson.annotate.JsonBackReference
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@NoArg
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    private var username: String = "",

    @Column(nullable = false)
    private var password: String = "******",

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonIgnore
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: Set<Role> = HashSet(),

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_additional_details")
    val userAdditionalDetail: UserAdditionalDetail? = null,

    @Transient
    private var authorities: MutableCollection<out GrantedAuthority> = HashSet(),

    @Column(name = "non_expired", nullable = false)
    private val nonExpired: Boolean = true,
    @Column(name = "non_locked", nullable = false)
    private val nonLocked: Boolean = true,
    @Column(nullable = false)
    private val enabled: Boolean = true,
    @Column(name = "credentials_non_expired", nullable = false)
    private val credentialsNonExpired: Boolean = true
) : UserDetails {

    override fun getUsername() = username
    fun setUsername(username_: String){
        username = username_
    }
    override fun getPassword() = password
    fun setPassword(password_: String){
        password = password_
    }
    override fun isAccountNonExpired() = nonExpired
    override fun isAccountNonLocked() = nonLocked
    override fun isEnabled() = enabled
    override fun isCredentialsNonExpired() = credentialsNonExpired
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities
    fun userDetails(): UserAdditionalDetail = userAdditionalDetail!!

    override fun hashCode(): Int {
        if (id == 0L) {
            return super.hashCode()
        }
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other === null || other !is User) {
            return false
        }
        if (this::class != other::class) {
            return false
        }
        return id == other.id
    }
}