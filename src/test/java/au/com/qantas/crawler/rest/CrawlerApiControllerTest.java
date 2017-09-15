package au.com.qantas.crawler.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Optional;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.common.io.Resources;

import au.com.qantas.crawler.CrawlerIntegrationTest;
import au.com.qantas.crawler.model.PageInfo;
import au.com.qantas.crawler.model.PageTreeInfo;
import au.com.qantas.crawler.service.CrawlerService;

/**
 * @author buddy
 *
 */
@RunWith(SpringRunner.class)
@CrawlerIntegrationTest
public class CrawlerApiControllerTest {

    @Inject
    private MockMvc mockMvc;
    private String htmlPayload;
    private PageTreeInfo pageTreeInfo;
    private PageInfo pageInfo;

    @MockBean
    private CrawlerService crawlerService;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        pageTreeInfo = new PageTreeInfo("something");
        htmlPayload = readContentAsString("sample.html");
        final Elements elements = Jsoup.parse(htmlPayload).select("a[href]");
        pageInfo = new PageInfo("Test", "http://test.com", elements);
    }

    @Test
    public void testGetWebPageTreeInfo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/crawler").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.when(crawlerService.crawl(Mockito.anyString())).thenReturn(Optional.of(pageInfo));
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/crawler?url=something&depth=5")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEmpty();

        Mockito.when(crawlerService.deepCrawl(Mockito.anyString(), Mockito.anyInt())).thenReturn(pageTreeInfo);
        mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/crawler?url=something&depth=5")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isNotEmpty().contains("something");

    }

    public static String readContentAsString(final String uri) throws IOException {
        return Resources.toString(Resources.getResource(uri), Charset.defaultCharset());
    }

}
