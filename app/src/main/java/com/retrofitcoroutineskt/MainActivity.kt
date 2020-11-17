package com.retrofitcoroutineskt

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.retrofitcoroutineskt.example1.DataRepository
import com.retrofitcoroutineskt.example1.PostModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: AlbumServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(this.javaClass.name, "onCreate Before retService")

        retService = RetrofitInstance.getRetrofitInstance()
            .create(AlbumServices::class.java)
        Log.e(this.javaClass.name, "onCreate After retService")

//        getRequestWithPathParameters()
//        getRequestWithQueryParameters()
//          uploadAlbum()

        updatePassword()

    }

    private fun getRequestWithQueryParameters() {
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retService.getSortedAlbum(3)
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    Log.i("MYTAG", albumItem.title)
                    val result = " " + "Album title : ${albumItem.title}" + "\n" +
                            " " + "Album id : ${albumItem.id}" + "\n" +
                            " " + "User id : ${albumItem.userId}" + "\n\n\n"

                    text_view.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        //path parameter example
        val pathResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }

        pathResponse.observe(this, Observer {
            val tittle = it.body()?.title
            Toast.makeText(applicationContext, tittle, Toast.LENGTH_LONG).show()
        })
    }

    private fun uploadAlbum() {
        val album = AlbumItem(1, "My Title", 3)
        val postResponse: LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }

        postResponse.observe(this, Observer {
            val receivedAlbumItem = it.body()
            val result = " " + "Album title : ${receivedAlbumItem?.title}" + "\n" +
                    " " + "Album id : ${receivedAlbumItem?.id}" + "\n" +
                    " " + "User id : ${receivedAlbumItem?.userId}" + "\n\n\n"

            text_view.text = result
        })
    }

    private fun updatePassword() {
        try {
            Log.e(this.javaClass.name, "updatePassword Start")

            val password = PasswordItemItem("11223344", "1113", "11223344")
            val postResponse: LiveData<Response<ApiResponse>> = liveData {
                Log.e(this.javaClass.name, "updatePassword liveData Start")

                Log.e(this.javaClass.name, "updatePassword liveData : password => $password")

                val response = retService.updatePassword2(password)
//                Log.e(this.javaClass.name, "updatePassword liveData : response => $response")

                emit(response)

                Log.e(this.javaClass.name, "updatePassword liveData End")

            }

            postResponse.observe(this, Observer {
                Log.e(this.javaClass.name, "updatePassword Observer Start")

                val receivePasswordItem = it.body()

//                Log.e(this.javaClass.name, "MY RESPONSE TRY => $receivePasswordItem")
//            val result = " " + "password : ${receivePasswordItem?.password}" + "\n" +
//                    " " + "Id Pegawai : ${receivePasswordItem?.id_pegawai}" + "\n" +
//                    " " + "New Password : ${receivePasswordItem?.new_password}" + "\n\n\n"
//            text_view.text = result
            })

            Log.e(this.javaClass.name, "updatePassword End")

        } catch (e: Exception) {
            Log.e(this.javaClass.name, "MY RESPONSE CATCH=> ${e.message}")

        }
    }

    //CONTOH EXAMPLE 1
//    private fun getUser() {
//        // get post data
//        val postServices = DataRepository.create()
//        postServices.getPost().enqueue(object : Callback<List<PostModel>> {
//
//            override fun onResponse(call: Call<List<PostModel>>, response: Response<List<PostModel>>) {
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    Log.e("tag", "responsennya ${data?.size}")
//
//                    data?.map {
//                        Log.e("tag", "datanya ${it.body}")
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<PostModel>>, error: Throwable) {
//                Log.e("tag", "errornya ${error.message}")
//            }
//        })
//    }

}