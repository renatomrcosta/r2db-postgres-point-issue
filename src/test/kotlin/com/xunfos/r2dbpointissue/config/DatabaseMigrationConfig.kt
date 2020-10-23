package com.xunfos.r2dbpointissue.config

import org.flywaydb.core.Flyway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.context.annotation.Configuration

/**
 * Manually run Flyway on start. I can't get this to play nice with r2dbc somehow
 */
@Configuration
class DatabaseMigrationConfig(
    private val flywayProperties: FlywayProperties
) {
    val logger: Logger = LoggerFactory.getLogger(DatabaseMigrationConfig::class.java.name)

    init {
        migrateDB()
    }

    private final fun migrateDB() {
        logger.info("Preparing DB Migration")
        try {
            Flyway
                .configure()
                .baselineOnMigrate(flywayProperties.isBaselineOnMigrate)
                .dataSource(flywayProperties.url, flywayProperties.user, flywayProperties.password)
                .load()
                .migrate()

            logger.info("DB Migration completed successfully")
        } catch (e: Exception) {
            logger.error("error migrating the db", e)
        }
    }
}
