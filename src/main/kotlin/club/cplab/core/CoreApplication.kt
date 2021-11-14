package club.cplab.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@ConfigurationPropertiesScan("club.cplab.core.config")
@SpringBootApplication
class CoreApplication

fun main(args: Array<String>) {
	runApplication<CoreApplication>(*args)
}
