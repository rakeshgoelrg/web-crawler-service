/**
 *
 */
package au.com.qantas.crawler.model;

import java.io.Serializable;

import org.jsoup.select.Elements;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author buddy
 *
 */
@Data
@AllArgsConstructor
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1993875051659981029L;

    private String title;

    private String url;

    private Elements links;
}
