package co.nyenjes.agent

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.ApplicationPidFileWriter
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import java.util.*
import javax.annotation.PostConstruct

@EntityScan("co.nyenjes.agent.*")
@SpringBootApplication
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AgentServer
    @PostConstruct
    fun main(args: Array<String>) {
        val objectMapper = ObjectMapper()
        objectMapper.findAndRegisterModules()

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val springApplication = SpringApplication(AgentServer::class.java)
        springApplication.addListeners(ApplicationPidFileWriter())     // register PID write to spring boot. It will write PID to file
        springApplication.run(*args)
    }
