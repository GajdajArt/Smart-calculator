package com.labralab.smartcalkulator.presenters

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.labralab.calk.repository.Repository
import com.labralab.smartcalkulator.App


import com.labralab.smartcalkulator.models.Expression
import com.labralab.smartcalkulator.presenters.adapters.ExpListAdapter
import javax.inject.Inject

/**
 * Created by pc on 09.03.2018.
 */
class ExpListPresenter {

    lateinit var adapter: ExpListAdapter

    @Inject
    lateinit var mainPresenter: MainPresenter

    init {
        App.appComponents.inject(this)
    }

    fun runList(context: Context, mainRecyclerView: RecyclerView){

        val layoutManager = LinearLayoutManager(context)
        adapter = ExpListAdapter(context)


        mainRecyclerView.layoutManager = layoutManager
        mainRecyclerView.adapter = adapter
        mainRecyclerView.addItemDecoration(DividerItemDecoration(context,
                LinearLayoutManager.VERTICAL))
    }

    fun addNewExpression(){

        mainPresenter.runExpFragment(null)
    }


}