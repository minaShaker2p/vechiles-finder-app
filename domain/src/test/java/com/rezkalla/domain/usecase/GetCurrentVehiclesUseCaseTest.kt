package com.rezkalla.domain.usecase

import com.rezkalla.domain.repository.CurrentVehiclesRepository
import com.rezkalla.domain.utils.TestDataGenerator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class GetCurrentVehiclesUseCaseTest {

    private lateinit var vehiclesUseCase: GetCurrentVehiclesUseCase

    @Mock
    lateinit var vehiclesRepository: CurrentVehiclesRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        vehiclesUseCase = GetCurrentVehiclesUseCase(
            vehiclesRepository,
            Schedulers.trampoline(), Schedulers.trampoline()
        )
    }

    @Test
    fun test_getCurrentVehicles_success() {

        val vehicles = TestDataGenerator.getCurrentVehicles()

        // when
        Mockito.`when`(vehiclesRepository.getCurrentVehicles())
            .thenReturn(Single.just(vehicles))

        // action
        val testObserver = vehiclesUseCase.buildUseCase().test()

        // verify
        testObserver
            .assertSubscribed()
            .assertValue { it.containsAll(vehicles) }
    }

    @Test
    fun test_getCurrentVehicles_error() {
        val errorMessage = " Error!!"

        // when
        Mockito.`when`(vehiclesRepository.getCurrentVehicles())
            .thenReturn(Single.error(Throwable(errorMessage)))

        // action
        val testObserver = vehiclesUseCase.buildUseCase().test()

        // verify
        testObserver
            .assertSubscribed()
            .assertError { it.message?.equals(errorMessage, false) ?: false }
            .assertNotComplete()
    }
}