package com.rezkalla.data.repository

import com.rezkalla.data.mapper.VehicleDataMapper
import com.rezkalla.data.source.RemoteDataSource
import com.rezkalla.data.utils.TestDataGenerator
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class CurrentVehicleRepositoryImplTest {

    @Mock
    lateinit var remoteDataSource: RemoteDataSource


    lateinit var vehicleDataMapper: VehicleDataMapper

    private lateinit var currentVehicleRepositoryImpl: CurrentVehicleRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        vehicleDataMapper = VehicleDataMapper()
        currentVehicleRepositoryImpl = CurrentVehicleRepositoryImpl(
            remoteDataSource,
            vehicleDataMapper
        )
    }

    @Test
    fun test_getCurrentVehicles_remote_integration_success() {
        //when
        val vehicles = TestDataGenerator.getCurrentVehicles()
        val vehiclesEntities = vehicles.map {
            vehicleDataMapper.from(it)
        }

        Mockito.`when`(remoteDataSource.getCurrentVehicles())
            .thenReturn(Single.just(vehicles))

        // action
        val testObserver = currentVehicleRepositoryImpl.getCurrentVehicles().test()

        // verify

        testObserver
            .assertSubscribed()
            .assertValue { it.containsAll(vehiclesEntities) }

        Mockito.verify(remoteDataSource, Mockito.times(1))
            .getCurrentVehicles()

    }

    @Test
    fun test_getCurrentVehicles_remote_integration_failure()
    {

        val errorMessage =" Error!!"
        // when
        Mockito.`when`(remoteDataSource.getCurrentVehicles())
            .thenReturn(Single.error(Throwable(errorMessage)))

        // action
        // action
        val testObserver = currentVehicleRepositoryImpl.getCurrentVehicles().test()


        // verify
        testObserver
            .assertSubscribed()
            .assertError { it.message?.equals(errorMessage, false) ?: false }
            .assertNotComplete()
    }


}