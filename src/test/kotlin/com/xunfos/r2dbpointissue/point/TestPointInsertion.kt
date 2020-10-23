package com.xunfos.r2dbpointissue.point

import com.xunfos.r2dbpointissue.repository.PointyTable
import com.xunfos.r2dbpointissue.repository.PointyTableRepository
import io.r2dbc.postgresql.codec.Point
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import java.util.UUID

@SpringBootTest
@ContextConfiguration
class TestPointInsertion {

    @Autowired
    private lateinit var pointyTableRepository: PointyTableRepository

    @Test
    fun `Should Manipulate Point Values From the DB`() {
        runBlocking {
            val migratedValue = pointyTableRepository.findById(
                UUID.fromString("0679a290-8015-473d-b4f4-3bbff0c35fdb")
            ).awaitSingle()

            // Able to fetch the values from the DB
            Assertions.assertEquals(53.5395, migratedValue.location.x)
            Assertions.assertEquals(10.0051, migratedValue.location.y)

            // Now trying to insert a new value:
            val uuid = UUID.randomUUID()
            val xCoordinate = 17.23
            val yCoordinate = 32.21
            pointyTableRepository.save(
                PointyTable(uuid, "New item that will fail", Point.of(xCoordinate, yCoordinate))
            )

            val insertedValue = pointyTableRepository.findById(uuid).awaitSingle()
            Assertions.assertEquals(xCoordinate, insertedValue.location.x)
            Assertions.assertEquals(yCoordinate, insertedValue.location.y)

        }
    }
}
