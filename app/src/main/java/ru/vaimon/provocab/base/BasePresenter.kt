package ru.vaimon.provocab.base

abstract class BasePresenter<V: BaseContract.View>: BaseContract.Presenter<V>{

    var viewIsAttached = false
    var mView: V? = null

    override fun attachView(view: V) {
        viewIsAttached = true
        mView = view
    }

    override fun detachView() {
        viewIsAttached = false
        mView = null
    }

}