package com.rezkalla.remote.source

import com.rezkalla.remote.api.VehiclesApiService
import com.rezkalla.remote.mapper.VehiclesDataNetworkMapper
import com.rezkalla.remote.utils.TestDataGenerator
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RemoteDataSourceImplTest {

    private lateinit var remoteDataSourceImpl: RemoteDataSourceImpl

    private lateinit var mapper: VehiclesDataNetworkMapper

    @Mock
    lateinit var vehiclesApiService: VehiclesApiService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mapper = VehiclesDataNetworkMapper()
        remoteDataSourceImpl = RemoteDataSourceImpl(vehiclesApiService, mapper)
    }

    @Test
    fun test_getCurrentVehicles_network_success() {

        //when
        val vehiclesNetworkResponse = TestDataGenerator.getFakeResponse()
        Mockito.`when`(vehiclesApiService.getCurrentVehicles())
            .thenReturn(Single.just(vehiclesNetworkResponse))

        //action
        val testObserver = remoteDataSourceImpl.getCurrentVehicles().test()

        // verify
        testObserver
            .assertSubscribed()
            .assertValue {
                it.containsAll(vehiclesNetworkResponse.data.vehicles.map { vehicle ->
                    mapper.from(vehicle)
                })
            }.assertComplete()

        Mockito.verify(vehiclesApiService, Mockito.times(1))
            .getCurrentVehicles()
    }


    @Test
    fun test_getCurrentVehicles_network_failure() {
        // when
        val errorMessage = "Error !!"
        Mockito.`when`(vehiclesApiService.getCurrentVehicles())
            .thenReturn(Single.error(Throwable(errorMessage)))

        //action
        val testObserver = remoteDataSourceImpl.getCurrentVehicles().test()

        //verify

        testObserver
            .assertSubscribed()
            .assertError {
                it.message?.contains(errorMessage) ?: false
            }.assertNotComplete()

        Mockito.verify(vehiclesApiService, Mockito.times(1))
            .getCurrentVehicles()
    }
}