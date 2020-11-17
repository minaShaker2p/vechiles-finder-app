package com.rezkalla.domain.usecase.base

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class SingleUseCase<T, in Input> constructor(
    private val backgroundScheduler: Scheduler,
    private val foregroundScheduler: Scheduler
) {
    protected abstract fun build(input: Input? = null): Single<T>

    operator fun invoke(input: Input? = null): Single<T> {
        return build(input)
            .subscribeOn(backgroundScheduler)
            .observeOn(foregroundScheduler)
    }

}