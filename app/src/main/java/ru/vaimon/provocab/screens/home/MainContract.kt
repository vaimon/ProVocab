package ru.vaimon.provocab.screens.home

import ru.vaimon.provocab.base.BaseContract

interface MainContract {
    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {

    }

    interface Repository {
    }
}