package com.app.airvet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.airvet.api.Resource
import com.app.airvet.api.connection.NetworkControl
import com.app.airvet.model.UserModel
import com.app.airvet.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MainVM @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkControl: NetworkControl

) : ViewModel() {
    //loading
    var isLoading = MutableLiveData(false)
    val isStillLoading get() = this.isLoading.value ?: false
    var maxCount: Int = 20
    var gender: String = ""

    companion object {
        private val TAG = MainVM::class.java.simpleName
    }


    //List
    private val _usersList = MutableLiveData<Resource<List<UserModel>>>()
    val usersList: LiveData<Resource<List<UserModel>>>
        get() = _usersList

    fun loadRandomUsers(page: Int) {
        Timber.e("eeee loadRandomUsers")
        if (!networkControl.isConnected()) {
            _usersList.postValue(Resource.error("Connection Error!", null))
            return
        }
        isLoading.postValue(true)

        viewModelScope.launch {
            val users = mainRepository.getRandomUsers(page, maxCount, gender)

            when (users.isSuccessful) {
                true ->
                    _usersList.postValue(Resource.success(users.body()!!.results))
                else ->
                    _usersList.postValue(Resource.error(users.message()))
            }
        }
    }
}