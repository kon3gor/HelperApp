package org.eshendo.helperapp.util

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCallback<T>(
    val result: (T) -> Unit,
    val error : (String) -> Unit
) : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.e("duck", "", t)
        error("Check logs")
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful){
            result(response.body()!!)
        }else{
            val res = "message is${response.message()} and code is ${response.code()}"
            error(res)
            Log.i("duck", res)
        }
    }
}