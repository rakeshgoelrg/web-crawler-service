package au.com.qantas.crawler.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.qantas.crawler.CrawlerIntegrationTest;
import au.com.qantas.crawler.model.PageInfo;
import au.com.qantas.crawler.model.PageTreeInfo;

@RunWith(SpringRunner.class)
@CrawlerIntegrationTest
public class CrawlerServiceImplTest {

    @Inject
    private CrawlerService crawlerService;

    @Test
    public void testDeepCrawl() {
        final PageTreeInfo info = crawlerService.deepCrawl("http://spring.io", 1);
        assertThat(info).isNotNull().satisfies(treeInfo -> {
            assertThat(treeInfo.getTitle()).contains("Spring");
            assertThat(treeInfo.getUrl()).contains("http://spring.io");
            assertThat(treeInfo.getNodes().size()).isGreaterThan(30);
        });
    }

    @Test
    public void testCrawl() {
        final Optional<PageInfo> info = crawlerService.crawl("http://google.com");
        assertThat(info).isPresent();
        assertThat(info.get().getTitle()).contains("Google");
        assertThat(info.get().getUrl()).contains("http://google.com");
        assertThat(info.get().getLinks().size()).isGreaterThan(20);
    }

}
