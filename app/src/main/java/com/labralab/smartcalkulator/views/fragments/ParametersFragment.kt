package com.labralab.calk.views.fragments


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.labralab.calk.repository.Repository
import com.labralab.smartcalkulator.App
import com.labralab.smartcalkulator.R
import com.labralab.smartcalkulator.presenters.ParamsPresenter
import kotlinx.android.synthetic.main.fragment_parameters.*
import javax.inject.Inject



class ParametersFragment : Fragment() {


    lateinit var hintTV: TextView
    lateinit var mainTV: TextView
    lateinit var fab: FloatingActionButton
    lateinit var resSP: Spinner
    lateinit var dRButton: ToggleButton


    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var paramsPresenter: ParamsPresenter


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        App.presenterComponents!!.inject(this)
        paramsPresenter.paramsFragment = this
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_parameters, container, false)
    }

    override fun onStart() {
        super.onStart()

        //Running view
        setViews()
        paramsPresenter.getResultList()
        paramsPresenter.showHint()
        paramsPresenter.changeMainTVContent()


        //Setting listeners to buttons
        buttonOnClick(oneButton, ParamsPresenter.ONE)
        buttonOnClick(twoButton, ParamsPresenter.TWO)
        buttonOnClick(threeButton, ParamsPresenter.THREE)
        buttonOnClick(fourButton, ParamsPresenter.FOUR)
        buttonOnClick(fiveButton, ParamsPresenter.FIVE)
        buttonOnClick(sixButton, ParamsPresenter.SIX)
        buttonOnClick(sevenButton, ParamsPresenter.SEVEN)
        buttonOnClick(eytButton, ParamsPresenter.EYT)
        buttonOnClick(nineButton, ParamsPresenter.NINE)
        buttonOnClick(zeroButton, ParamsPresenter.ZERO)

        nextVarButton.setOnClickListener {
            paramsPresenter.nextOrDone()
        }

        pointButton.setOnClickListener {
            paramsPresenter.insertPoint()
        }

        cancelButton.setOnClickListener {
            paramsPresenter.cancel()
        }

        saveResultButton.setOnClickListener {
            paramsPresenter.saveResult()
        }

        delResultButton.setOnClickListener {
            paramsPresenter.removeResult()
        }

        pasteResultButton.setOnClickListener {
            paramsPresenter.pasteResult()
        }

        //listening to a numAfterPoint (SeekBar)
        numAfterPoint.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                paramsPresenter.changeNumAfterPoint(seekBar.progress)
            }
        })

    }

    private fun buttonOnClick(button: Button, flag: Int) {
        button.setOnClickListener {
            paramsPresenter.setNumber(flag)
        }
    }

    //View params for a presenter
    private fun setViews() {

        hintTV = expTitleInPF
        mainTV = distInPF
        fab = nextVarButton
        resSP = resultSP
        dRButton = degOrRadButton
    }
}
