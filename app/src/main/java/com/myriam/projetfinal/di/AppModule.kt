package com.myriam.projetfinal.di

// --- Ensure ALL necessary imports are present ---
import com.google.gson.GsonBuilder
import com.myriam.projetfinal.data.repositories.ExercisesRepositoryImpl
import com.myriam.projetfinal.data.repositories.UserRepositoryImpl
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository
import com.myriam.projetfinal.screens.exercises_screen.ExerciseViewModel
import com.myriam.projetfinal.screens.home_screen.HomeScreenViewModel
import com.myriam.projetfinal.screens.login_screen.LoginScreenViewModel
import com.myriam.projetfinal.screens.signup_screen.SignupScreenViewModel
import com.myriam.projetfinal.viewmodels.RootViewModel // Check path
import com.myriam.projetfinal.daily_challenge.DailyChallengeViewModel // Check path
import com.myriam.projetfinal.data.network.api.ApiService
import com.myriam.projetfinal.data.repositories.interfaces.ExercisesRepository
import com.myriam.projetfinal.screens.ProfileScreen.ProfileScreenViewModel
// *** IMPORT THE PROFILE VIEWMODEL ***
 // <-- Make sure this import is correct

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


const val BASE_URL = "https://api.darresni.io/api/v1/"

fun provideUnsafeOkHttpClient(): OkHttpClient {
    val trustAllCertificates = arrayOf<TrustManager>(
        object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    )

    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCertificates, SecureRandom())
    val sslSocketFactory = sslContext.socketFactory

    val builder = OkHttpClient.Builder()
    builder.sslSocketFactory(sslSocketFactory, trustAllCertificates[0] as X509TrustManager)
    builder.hostnameVerifier { _, _ -> true } // Bypass hostname verification

    return builder.build()
}

val networkModule = module {
    single {
        GsonBuilder().create()
    }

    single { provideUnsafeOkHttpClient() }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get())) // Use GsonConverterFactory
            .client(get()) // Use the unsafe OkHttpClient
            .build()
            .create(ApiService::class.java) // Create an instance of your ApiService
    }
}

val appModule = module {
    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<ExercisesRepository> { ExercisesRepositoryImpl(get()) } // Assuming you have this interface

    // ViewModels
    viewModel { RootViewModel(get(), get()) }
    viewModel { ExerciseViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
    viewModel { LoginScreenViewModel(get()) }
    viewModel { SignupScreenViewModel(get()) }
    viewModel { DailyChallengeViewModel(get(), get()) }
    viewModel { ProfileScreenViewModel(get()) }
}