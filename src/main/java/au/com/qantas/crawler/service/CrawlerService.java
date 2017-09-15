package au.com.qantas.crawler.service;

import java.util.Optional;

import au.com.qantas.crawler.model.PageInfo;
import au.com.qantas.crawler.model.PageTreeInfo;

/**
 * @author buddy
 *
 */
public interface CrawlerService {

    /**
     * Deep crawl the page for provided depth but upto max
     *
     * @param url
     *            web page url to crawl
     * @param depth
     *            w.r.t base page url
     * @return page info upto desired or max depth
     */
    public PageTreeInfo deepCrawl(final String url, final int depth);

    /**
     * get page info for given url
     *
     * @param url
     *            web page url
     * @return optional page info - url, title and links on a web page
     */
    public Optional<PageInfo> crawl(String url);
}
