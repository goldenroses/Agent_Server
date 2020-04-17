package co.nyenjes.agent.config

import mu.KotlinLogging
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

private val logger = KotlinLogging.logger {}

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "agentTransactionManager", basePackages = arrayOf("co.nyenjes.agent.repository"), entityManagerFactoryRef = "agentEntityManagerFactory")
class PrestoConfig {
    @Bean(name = arrayOf("agentDataSource"))
    @ConfigurationProperties(prefix = "agent.datasource")
    @Primary
    fun dataSource(): DataSource {

        if (System.getenv("SPRING_PROFILES_ACTIVE") == "prod") {
            val flyway = Flyway.configure()

            flyway.schemas(System.getenv("SCHEMA_NAME")).dataSource(System.getenv("SPRING_DATASOURCE_URL"), System.getenv("SPRING_DATASOURCE_USERNAME"), System.getenv("SPRING_DATASOURCE_PASSWORD")).load().migrate()
            logger.info { "Running migration with: \n SCHEMA_NAME: ${System.getenv("SCHEMA_NAME")}" }
            logger.info { "Running migration with: \n SPRING_DRIVER_CLASS_NAME: ${System.getenv("SPRING_DRIVER_CLASS_NAME")}" }
        }

        val dataSource = DataSourceBuilder.create()

        val ds = dataSource.driverClassName(System.getenv("SPRING_DRIVER_CLASS_NAME")).build()
        return ds
    }

    @Bean(name = arrayOf("agentEntityManagerFactory"))
    fun agentEntityManagerFactory(
        builder: EntityManagerFactoryBuilder, @Qualifier("agentDataSource") dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages("co.nyenjes.agent.model", "co.nyenjes.agent.domain").persistenceUnit("agent")
            .build()
    }

    @Bean(name = arrayOf("agentTransactionManager"))
    fun agentTransactionManager(
        @Qualifier("agentEntityManagerFactory") agentEntityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(agentEntityManagerFactory)
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {

        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry!!.addMapping("/**").allowedOrigins(
                    "http://localhost:3000"
                ).allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                 .allowedHeaders("Authorization", "origin", "content-type", "accept", "x-requested-with")
            }
        }
    }
}
