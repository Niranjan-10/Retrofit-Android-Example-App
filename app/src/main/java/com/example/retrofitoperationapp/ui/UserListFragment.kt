package com.example.retrofitoperationapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitoperationapp.R
import com.example.retrofitoperationapp.data.Data
import com.example.retrofitoperationapp.databinding.UserListFragmentBinding
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserListFragment : Fragment(R.layout.user_list_fragment){

    private lateinit var viewModel: UserListViewModel
    private val userListAdapter = UserListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        viewModel.fetch()

        user_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner, Observer { users ->

            users.let {
                user_list.visibility = View.VISIBLE
                userListAdapter.setUser(users)
            }

        })

        viewModel.userError.observe(viewLifecycleOwner, {
            list_error.visibility = if (it == " ") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->

            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE

                if (it) {
                    list_error.visibility = View.GONE
                    user_list.visibility = View.GONE
                }
            }


        })
    }




}