package org.eshendo.helperapp.ui

import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.eshendo.helperapp.R
import org.eshendo.helperapp.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.WHITE
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.i("duck", "${grantResults[0]} ${grantResults[1]} ${grantResults[2]}")
        Log.i("duck", "${grantResults.size}")
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED
            || grantResults[1] != PackageManager.PERMISSION_GRANTED
            || grantResults[2] != PackageManager.PERMISSION_GRANTED ){

            findNavController(R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_appCannotWorkDialog)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}