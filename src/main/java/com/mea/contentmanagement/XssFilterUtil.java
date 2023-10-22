/**
 * Copyright TCS. All Rights Reserved.
 * This software is the proprietary to TCS.
 * Use is subject to license terms.
 */
package com.mea.contentmanagement;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

/**
 * The Class XssFilterUtil.
 *
 * @author TCS
 */
public class XssFilterUtil {

    /**
     * Instantiates a new xss filter util.
     */
    private XssFilterUtil() {

        super();

    }

    /**
     * Use your own basic WithImages whitelist The allowable notes are
     * a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
     * strike,strong,sub,sup,u,ul,img And the src,align,alt,height,width,title
     * attributes of href,img tags of a tag.
     */
    private static final Whitelist whitelist = Whitelist.none();

    /** The Constant outputSettings. */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings()
            .prettyPrint(false);
    static {
        // Some styles are implemented using style in rich text editing
        // For example, the red font style="color:red;"
        // So you need to add style attributes to all tags
        whitelist.addAttributes(":all", "style");
    }

    /**
     * Clean.
     *
     * @param content
     *            the content
     * @return the string
     */
    public static String clean(String content) {
        return Jsoup.clean(content, "", whitelist, outputSettings);
    }
}