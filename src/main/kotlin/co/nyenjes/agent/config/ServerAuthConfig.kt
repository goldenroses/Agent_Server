package co.nyenjes.agent.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

@Configuration
@EnableResourceServer
class OAuth2ResourceServerConfiguration(
    private val tokenStore: TokenStore
) : ResourceServerConfigurerAdapter() {
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources
            .tokenStore(tokenStore)
            .resourceId(AGENT_RESOURCE_ID)
    }

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        // @formatter:on
    }

    companion object {
        val AGENT_RESOURCE_ID = "inventory"
    }
}

@Configuration
@EnableAuthorizationServer

class ServerAuthConfig(
    @Qualifier("agentDataSource")
    private val dataSource: DataSource,

    @Autowired
    private val authenticationManager: AuthenticationManager,

    @Qualifier("passwordEncoder")
    private val passwordEncoder: PasswordEncoder

) : AuthorizationServerConfigurer {

    @Bean
    fun tokenStore(): JdbcTokenStore {
        return JdbcTokenStore(dataSource)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!.checkTokenAccess("isAuthenticated()").checkTokenAccess("permitAll()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.jdbc(dataSource).passwordEncoder(passwordEncoder)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!.tokenStore(tokenStore())
        endpoints.authenticationManager(authenticationManager)
    }

}
