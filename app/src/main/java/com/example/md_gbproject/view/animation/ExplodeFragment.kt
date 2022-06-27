package com.example.md_gbproject.view.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentExplodeBinding
import com.example.md_gbproject.viewmodel.AppViewModel

class ExplodeFragment : Fragment() {

    private var _binding: FragmentExplodeBinding? = null
    private val binding: FragmentExplodeBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExplodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.adapter = Adapter()

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ExplodeFragment()
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.fragment_recycle_item_button,
                    parent,
                    false,
                ) as View
            )

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener { button ->
                //explode(it)
                val epicenter = Rect()
                button.getGlobalVisibleRect(epicenter)
                val transitionExplode = Explode()
                transitionExplode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return epicenter
                    }
                }
                transitionExplode.duration = 5000
                transitionExplode.excludeTarget(button, true)
                val transitionFadeButton = Fade()
                transitionFadeButton.duration = 999999
                val transitionSet = TransitionSet()
                transitionSet.addTransition(transitionFadeButton)
                transitionSet.addTransition(transitionExplode)
                TransitionManager.beginDelayedTransition(binding.recycleView, transitionSet)
                binding.recycleView.adapter = null
            }
        }

        override fun getItemCount(): Int {
            return 20
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}