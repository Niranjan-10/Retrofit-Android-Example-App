package com.example.retrofitoperationapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitoperationapp.api.UserService
import com.example.retrofitoperationapp.data.Data
import kotlinx.coroutines.*

class UserListViewModel : ViewModel() {
    val userService = UserService().getUserService()
    var job: Job? = null;

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("CoroutineExceptionHandler got $throwable")
    }


    val users = MutableLiveData<List<Data>>()
    val loading = MutableLiveData<Boolean>()
    val userError = MutableLiveData<String>()

    fun fetch() {
        fetchUsers()
    }


    private fun fetchUsers() {
        loading.value = true
        Log.d("Users","working 1")
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = userService.getUsers(RESULTS)
            Log.d("Users","working 2")
            Log.d("Users","${response.body()?.data}")
            if (response.isSuccessful) {

                withContext(Dispatchers.Main) {

                    users.value = response.body()?.data

                }
            } else {
                Log.d("Users","error")
                onError("Error : ${response.errorBody()}")
            }
        }

        userError.value = " "
        loading.value = false

    }

    private fun onError(message: String) {
        userError.value = message
        loading.value = false
        Log.d("error", "$message")
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


    companion object {
        private const val RESULTS = 2
    }
}