package com.xunfos.r2dbpointissue

import com.xunfos.r2dbpointissue.repository.PointyTable
import com.xunfos.r2dbpointissue.repository.PointyTableRepository
import io.r2dbc.postgresql.codec.Point
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.asPublisher
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class HandlingTests(
    private val repository: PointyTableRepository,
) {
    @Test
    fun `simple showcase of dealing with futures and fluxes`() = runBlocking {
        // Value already exists in the db, my entity is called PointyTable. Don't ask
        val find: PointyTable = repository.findById(
            UUID.fromString("0679a290-8015-473d-b4f4-3bbff0c35fdb")
        ).awaitSingle()

        val findOrNull: PointyTable = repository.findById(
            UUID.randomUUID()
        ).awaitFirstOrNull() ?: getRandomValue() // you can write custom behavior here!

        val findMany: Flow<PointyTable> = repository.findAll().asFlow()

        val flowOfEntities: Flow<PointyTable> = flowOf(getRandomValue(), getRandomValue())
        val savedValues = repository.saveAll(flowOfEntities.asPublisher()).asFlow()

        //operating / collecting
        savedValues.collect {
            println("My Saved Values: $it")
        }
    }

    private fun getRandomValue(): PointyTable = PointyTable(
        id = UUID.randomUUID(),
        description = "Some description",
        location = Point.of(1.0, 2.2),
    )
}