package com.xunfos.r2dbpointissue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@EnableR2dbcRepositories
@EnableConfigurationProperties(FlywayProperties::class)
@SpringBootApplication
class R2dbPointIssueApplication

fun main(args: Array<String>) {
    runApplication<R2dbPointIssueApplication>(*args)
}
