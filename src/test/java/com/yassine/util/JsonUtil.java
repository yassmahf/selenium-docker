package com.yassine.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yassine.tests.vendorportal.model.VendorPortalTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper ();


    public static <T> T getTestData(String path, Class<T> clazz) {
        try (InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream, clazz);
        } catch (Exception e) {
            log.error("Unable to read test data {}", path, e);
        }
        return null;
    }


}
