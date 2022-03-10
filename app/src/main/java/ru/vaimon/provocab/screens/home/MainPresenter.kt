package ru.vaimon.provocab.screens.home

import ru.vaimon.provocab.base.BasePresenter

class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val mRepository: MainContract.Repository by lazy { MainRepository(this) }
}