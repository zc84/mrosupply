package com.utils;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dmitry Sherstobitov
 */
public class Parser {

    private static final String agent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.0.1-SNAPSHOT312.57 Safari/537.17";
    private static Logger logger = Logger.getLogger(Parser.class);

    public static Document getContent(String html) {
        logger.info("Parsing html content");
        return Jsoup.parse(html);
    }

    public static boolean search_text_in_html(Document content, String text, String locator) {
        boolean isFound = false;
        text = text.replaceAll("\\s+", "");

        logger.info("Searching " + text);
        for (Element row : content.select(locator)) {
            String rowText = row.text().replaceAll("\\s+", "");

            logger.info("comparing " + rowText + " with " + text);
            if (rowText.contains(text)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public static Document getContent(Method method, String URL) throws Exception {
        Response res = null;
        int attempt = 0;
        String error;

        do {
            try {
                res = Jsoup.connect(URL).timeout(60 * 1000).userAgent(agent).method(method).execute();
                error = "";
                break;
            } catch (Exception e) {
                error = e.getMessage();
                attempt++;
            }
        } while (attempt < 3);

        if (error.isEmpty() && res != null) {
            return res.parse();
        } else {
            throw new Exception(error);
        }
    }

    public static Document getContent(Method method, String URL, Map<String, String> cookies) throws IOException {

        Response res = Jsoup.connect(URL).cookies(cookies).timeout(60 * 1000).userAgent(agent).method(method).execute();
        return res.parse();
    }

    public static Map<String, String> setParams(String[] additionalParams) {
        Map<String, String> params = new HashMap<String, String>();

        for (String pars : additionalParams) {
            try {
                params.put(pars.split(",")[0], pars.split(",")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                params.put(pars.split(",")[0], "");
            }
        }
        return params;
    }

    public static String getRegex(String string, String regex) {
        logger.info("Getting " + regex + " regex from " + string);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);

        if (m.find()) {
            return m.group();
        } else {
            return "";
        }
    }

    public Document getContent(Method method, String URL, Map<String, String> params, Map<String, String> cookies) throws IOException {
        Response res = Jsoup.connect(URL).data(params).cookies(cookies).timeout(60 * 1000).userAgent(agent).method(method).execute();
        return res.parse();
    }
}
