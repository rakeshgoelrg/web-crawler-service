package au.com.rakesh.crawler.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.rakesh.crawler.configuration.AppProperties.CrawlerProperties;
import au.com.rakesh.crawler.model.PageTreeInfo;
import au.com.rakesh.crawler.service.CrawlerService;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-02T22:39:21.740Z")

@Controller
@Slf4j
public class CrawlerApiController implements CrawlerApi {

    @Inject
    private CrawlerService crawlerService;

    @Value("#{appProperties.crawler}")
    private CrawlerProperties crawlerProperties;

    @Override
    @GetMapping(value = "/crawler", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PageTreeInfo> getWebPageTreeInfo(
            @NotNull @RequestParam(value = "url", required = true) final String url,
            @RequestParam(value = "depth", required = false) final Integer depth) {

        log.info("Request for deep crawling received for url: {}, depth: {}", url, depth);
        final int newDepth = Integer.min(Optional.ofNullable(depth).orElse(crawlerProperties.getDefaultDepth()),
                crawlerProperties.getMaxDepthAllowed());
        log.info(
                "Depth might be optimized to go upto Max defined in property:'app.crawler.max-depth-allowed'. optimized depth: {}",
                newDepth);
        return new ResponseEntity<>(crawlerService.deepCrawl(url, depth, null), HttpStatus.OK);
    }

}
