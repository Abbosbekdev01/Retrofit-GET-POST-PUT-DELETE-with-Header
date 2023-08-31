package uz.abbosbek.crudtaks_4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.abbosbek.crudtaks_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}