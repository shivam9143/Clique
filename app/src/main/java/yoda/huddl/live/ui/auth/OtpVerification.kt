package yoda.huddl.live.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import yoda.huddl.live.R
import yoda.huddl.live.databinding.FragmentOtpVerificationBinding


class OtpVerification : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentOtpVerificationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpVerificationBinding.inflate(inflater)
        init()
        return binding.root
    }

    fun init() {
        arguments?.let {
            var otp = it.getString("otp")
            var mobile = it.getString("mobile")
            binding.OtpSentMobTitleTv.text = getString(R.string.otp_sent_to, mobile)
            if (otp != null) {
                binding.otpView.setOTP(otp)
            }
        }

        binding.btnVerifyOtp.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnVerifyOtp.id -> {
                getOtp()?.let {
                    (activity as AuthActivity).clickOnVerifyPhoneBtn(it) {

                    }
                } ?: run {

                }
            }

        }
    }

    fun getOtp(): String? {
        var otp = binding.otpView.otp
        if (otp != null && otp?.length < 6) {
            binding.otpView.showError()
        }
        return otp.toString()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtpVerification().apply {
                arguments = Bundle().apply {

                }
            }
    }
}