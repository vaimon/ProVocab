package ru.vaimon.provocab.base


interface BaseContract{

    interface View

    interface Presenter<V: View>{

        fun attachView(view: V)
        fun detachView()
    }

}