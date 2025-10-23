package com.springrest.springrest.shortUrl;

import java.security.SecureRandom;
import java.util.HashMap;

public class XUrlImpl implements XUrl {

    private HashMap<String, String> longToShortUrlMap = new HashMap<>();
    private HashMap<String, String> shortToLongUrlMap = new HashMap<>();
    private HashMap<String, Integer> hitCountMap = new HashMap<>();
    private SecureRandom random = new SecureRandom();

    public XUrlImpl() {
        for (String longUrl : longToShortUrlMap.keySet()) {
            hitCountMap.put(longUrl, 0);
        }
    }

    @Override
    public String registerNewUrl(String longUrl) {

        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }

        String val = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrlBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int randomIndex = random.nextInt(val.length());
            shortUrlBuilder.append(val.charAt(randomIndex));
        }
        String shorturl = "http://short.url/" + shortUrlBuilder.toString();
        longToShortUrlMap.put(longUrl, shorturl);
        shortToLongUrlMap.put(shorturl, longUrl);
        return shorturl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {

        if (shortToLongUrlMap.containsKey(shortUrl)) {
            return null;
        } else if (longToShortUrlMap.containsKey(longUrl)
                && !longToShortUrlMap.get(longUrl).equals(shortUrl)) {
            return null;
        } else {
            String val = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder shortUrlBuilder = new StringBuilder();
            for (int i = 0; i <= 8; i++) {
                int randomIndex = random.nextInt(val.length());
                shortUrlBuilder.append(val.charAt(randomIndex));
            }
            shortUrl = "http://short.url/" + shortUrlBuilder;
            longToShortUrlMap.put(longUrl, shortUrl);
            shortToLongUrlMap.put(shortUrl, longUrl);

        }
        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {

        String longURL = null;
        if (shortToLongUrlMap.containsKey(shortUrl)) {
            longURL = shortToLongUrlMap.get(shortUrl);
            hitCountMap.put(longURL, hitCountMap.getOrDefault(longURL, 0) + 1);
            return longURL;
        } else {
            return null;
        }

    }

    @Override
    public Integer getHitCount(String longUrl) {

        return hitCountMap.getOrDefault(longUrl, 0);
    }

    @Override
    public String delete(String longUrl) {

        if (longToShortUrlMap.containsKey(longUrl)) {
            String shortUrl = longToShortUrlMap.get(longUrl);
            longToShortUrlMap.remove(longUrl);
            shortToLongUrlMap.remove(shortUrl);
            return shortUrl;
        } else {
            return longUrl;
        }
    }

}
