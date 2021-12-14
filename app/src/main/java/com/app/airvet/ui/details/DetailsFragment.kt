package com.app.airvet.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.app.airvet.MainVM
import com.app.airvet.common.BaseFragment
import com.app.airvet.databinding.FragmentDetailsBinding
import com.app.airvet.model.UserModel
import com.app.healios.ui.common.navigateTo


class DetailsFragment : BaseFragment() {

    private val viewModel: MainVM by activityViewModels()
    //private var topBarBinding: LayoutTopBarPostListBinding? = null

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserData: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set top bar binding for buttons.
        //val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //topBarBinding = LayoutTopBarPostListBinding.inflate(inflater)

        // Get arguments to pass data from other fragments.
        val args: DetailsFragmentArgs by navArgs()
        mUserData = args.userModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.user = mUserData
        hideActionBar()
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.iconBack.setOnClickListener {
            navigateTo(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}