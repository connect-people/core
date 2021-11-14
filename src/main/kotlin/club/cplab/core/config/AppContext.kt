package club.cplab.core.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppContext {
    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        modelMapper.configuration.isAmbiguityIgnored = false
        return modelMapper
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper().registerKotlinModule()
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
        return mapper
    }
}