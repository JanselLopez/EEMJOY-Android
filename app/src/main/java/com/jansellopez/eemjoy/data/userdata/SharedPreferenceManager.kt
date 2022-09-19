package com.jansellopez.eemjoy.data.userdata

import android.content.Context
import com.jansellopez.eemjoy.data.model.Token
import com.jansellopez.eemjoy.data.model.User

class SharedPreferenceManager private constructor(private val mCtx: Context){

    val isLoggedIn:Boolean
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return !sharedPreferences.getString("id",null).isNullOrEmpty()
    }
    val user:User
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val email: String? = sharedPreferences.getString("email","")
        val password:String? = sharedPreferences.getString("password","")
        return User(
            email?:"",
            password?:""
        )
    }
    fun saveUser(user: User){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email",user.email)
        editor.putString("password",user.password)
        editor.apply()
    }

    val token:Token
    get() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val type = sharedPreferences.getString("type","")
        val body = sharedPreferences.getString("token","")
        val username:String? = sharedPreferences.getString("username","")
        return Token(
            body?:"",
            type?:"",
            username?:""
        )
    }

    fun saveToken(token: Token){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("type",token.token_type)
        editor.putString("token",token.access_token)
        editor.putString("username",token.username)
        editor.apply()
    }

    fun clear(){
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object{
        private const val SHARED_PREF_NAME = "user_preference"
        private var mInstance:SharedPreferenceManager? = null
        @Synchronized
        fun getINstance(mCtx: Context):SharedPreferenceManager{
            if(mInstance == null){
                mInstance = SharedPreferenceManager(mCtx)
            }
            return mInstance as SharedPreferenceManager
        }
    }
}