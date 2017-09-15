package au.com.rakesh.crawler.configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan("au.com.rakesh.crawler")
@SpringBootConfiguration
public class ApplicationConfiguration {

}
