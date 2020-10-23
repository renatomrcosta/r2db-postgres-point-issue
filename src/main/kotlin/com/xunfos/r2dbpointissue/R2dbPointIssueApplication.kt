package com.xunfos.r2dbpointissue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(FlywayProperties::class)
@SpringBootApplication
class R2dbPointIssueApplication

fun main(args: Array<String>) {
    runApplication<R2dbPointIssueApplication>(*args)
}
