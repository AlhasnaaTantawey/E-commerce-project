package com.example.e_commerceproject.data.models

import java.lang.Exception


sealed class Resource <T>(
     val data: T? =null,
     val exception: Exception? =null
 ){
    class Success<T>(data: T) :Resource<T>(data)

    class Loading<T>(data: T?=null) :Resource<T>(data)

    class Error<T>(message :Exception,data: T?=null) :Resource<T>(data , message)

    override fun toString(): String {
        return when(this){
            is Success -> "sucess [data =$data]"
            is Loading -> "loading [data=$data]"
            is Error   -> "error [ eceeption =${exception } , data=$data]"
        }
    }
}