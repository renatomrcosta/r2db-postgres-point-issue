package com.xunfos.r2dbpointissue.repository

import io.r2dbc.postgresql.codec.Point
import org.springframework.data.annotation.Id
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.UUID

data class PointyTable(
    @Id
    val id: UUID,
    val description: String,
    val location: Point,
)

interface PointyTableRepository : ReactiveCrudRepository<PointyTable, UUID>
