package com.ubaya.adv160420119week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    var result = 0
    var  total = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    fun random(){
        val num1 = (0..100).shuffled().last()
        val num2 = (0..100).shuffled().last()
        txtNum1.text = num1.toString()
        txtNum2.text = num2.toString()
        result = num1 + num2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        random()
        if (arguments != null) {
            val playerName = GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
        }
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            if (txtAnswer.text.toString() == result.toString()){
                random()
                txtAnswer.setText("")
                total++
            }
            else{
                val action = GameFragmentDirections.actionGameFragmentToResultFragment(total)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}