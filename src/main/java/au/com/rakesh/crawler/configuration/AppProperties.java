package au.com.rakesh.crawler.configuration;

import javax.inject.Named;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * JavaBean bound to Application properties in application configuration.
 */
@Data
@Named
@ConfigurationProperties(prefix = "app")
@Validated
public class AppProperties {

    @Valid
    private final CrawlerProperties crawler = new CrawlerProperties();

    /**
     * Nested property group bound to 'rakesh.crawler'.
     */
    @Data
    @Validated
    public static class CrawlerProperties {

        /**
         * default depth for web crawler.
         */
        @Min(0)

        private int defaultDepth;

        /**
         * max depth allowed for a request based on service capability and SLAs to
         * prevent DOS
         */
        @Min(0)
        private int maxDepthAllowed;

        /**
         *
         * timeout for http requests in seconds
         */
        @Min(5)
        private int timeOut;

        /**
         * follow redirects for the given url
         */
        private boolean followRedirects;

    }
}
