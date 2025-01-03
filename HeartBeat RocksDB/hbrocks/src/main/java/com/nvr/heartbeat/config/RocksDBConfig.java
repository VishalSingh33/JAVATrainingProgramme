package com.nvr.heartbeat.config;

import org.rocksdb.RocksDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import org.rocksdb.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.rocksdb.RocksDB;

@Configuration
public class RocksDBConfig {
    private static final Logger logger = LoggerFactory.getLogger(RocksDBConfig.class);
    @Value("${rocksdb.path}")
    private String dbPath;

    static {
        RocksDB.loadLibrary();
    }

    @Bean
    public RocksDB rocksDB() {
        try {
            // Create the RocksDB directory if it doesn't exist
            File dbDir = new File(dbPath);
            if (!dbDir.exists() && !dbDir.mkdirs()) {
                throw new IOException("HB_CONF_RDB - Failed to create RocksDB directory at: " + dbPath);
            }

            // Set up RocksDB options
        @SuppressWarnings("resource")
        Options options = new Options().setCreateIfMissing(true);
        
        logger.info("HB_CONF_RDB - DB Path: {}", new File(dbPath).getAbsolutePath());
        
        // Return the RocksDB instance
        return RocksDB.open(options, dbPath);
        } catch (IOException | RocksDBException e) {
            logger.error("HB_CONF_RDB - RocksDB initialization error: {}", e.getMessage(), e);
            throw new IllegalStateException("Failed to initialize RocksDB", e);
        }
    }

}