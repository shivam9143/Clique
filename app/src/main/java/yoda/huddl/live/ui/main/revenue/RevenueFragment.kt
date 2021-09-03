package yoda.huddl.live.ui.main.revenue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yoda.huddl.live.R
import yoda.huddl.live.databinding.FragmentRevenueBinding

class RevenueFragment : Fragment() {

    lateinit var fragmentRevenueBinding: FragmentRevenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentRevenueBinding.inflate(inflater)
        fragmentRevenueBinding = binding
        return binding.root
    }

}