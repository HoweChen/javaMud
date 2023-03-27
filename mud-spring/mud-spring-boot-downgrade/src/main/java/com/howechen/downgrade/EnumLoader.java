package com.howechen.downgrade;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.alibaba.csp.sentinel.log.RecordLog;
import com.alibaba.csp.sentinel.spi.Spi;
import com.alibaba.csp.sentinel.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class EnumLoader<E> {
    Logger log = LoggerFactory.getLogger(EnumLoader.class);

    public void load(String fileName, Class<E> enumClass) {

        String fullFileName = fileName;
        ClassLoader classLoader;
        Enumeration<URL> urls = null;
        try {
            urls = Thread.currentThread().getContextClassLoader().getResources(fullFileName);
        } catch (IOException e) {
            log.warn("The file {} is not found", fullFileName);
        }

        if (urls == null || !urls.hasMoreElements()) {
//            log.warn("No SPI configuration file, filename=" + fullFileName + ", classloader=" + classLoader);
            return;
        }

        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();

            InputStream in = null;
            BufferedReader br = null;
            try {
                in = url.openStream();
                br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                String line;
                while ((line = br.readLine()) != null) {
                    if (StringUtil.isBlank(line)) {
                        // Skip blank line
                        continue;
                    }

                    line = line.trim();
                    int commentIndex = line.indexOf("#");
                    if (commentIndex == 0) {
                        // Skip comment line
                        continue;
                    }

                    if (commentIndex > 0) {
                        line = line.substring(0, commentIndex);
                    }
                    line = line.trim();

//                    log.info("[SpiLoader] Found SPI implementation for SPI {}, provider={}, aliasName={}" + ", isSingleton={}, isDefault={}, order={}", service.getName(), line, aliasName, spi == null ? true : spi.isSingleton(), spi == null ? false : spi.isDefault(), spi == null ? 0 : spi.order());
                }
            } catch (IOException e) {
                log.error("error reading SPI configuration file", e);
            } finally {
                closeResources(in, br);
            }
        }
    }

    private void closeResources(Closeable... closeables) {
        if (closeables == null || closeables.length == 0) {
            return;
        }

        Exception firstException = null;
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
                if (firstException == null) {
                    firstException = e;
                }
            }
        }
        if (firstException != null) {
//            fail("error closing resources", firstException);
        }
    }
}
