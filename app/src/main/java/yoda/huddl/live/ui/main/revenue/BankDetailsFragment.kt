package yoda.huddl.live.ui.main.revenue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yoda.huddl.live.R
import yoda.huddl.live.databinding.FragmentBankDetailsBinding

class BankDetailsFragment : Fragment() {

    lateinit var fragmentBankDetailsBinding: FragmentBankDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentBankDetailsBinding.inflate(inflater)
        fragmentBankDetailsBinding = binding
        return binding.root
    }

}