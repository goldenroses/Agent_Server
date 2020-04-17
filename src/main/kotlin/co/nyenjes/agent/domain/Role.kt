package co.nyenjes.agent.domain

import co.nyenjes.agent.annotation.NoArg
import com.fasterxml.jackson.annotation.*
import org.springframework.transaction.annotation.Transactional
import java.io.Serializable
import javax.persistence.*

@Entity
@NoArg
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonIgnore
    @JoinTable(
        name = "role_permission",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    val permissions: Set<Permission> = HashSet(),

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    val users: Set<User> = HashSet()
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other === null || other !is Role) {
            return false
        }
        if (this::class != other::class) {
            return false
        }
        return name == other.name
    }
}