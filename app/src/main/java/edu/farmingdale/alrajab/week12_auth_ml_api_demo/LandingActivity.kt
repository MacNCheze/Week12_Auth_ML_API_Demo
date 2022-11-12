package edu.farmingdale.alrajab.week12_auth_ml_api_demo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import edu.farmingdale.alrajab.week12_auth_ml_api_demo.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityLandingBinding
    lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logoutBtn.setOnClickListener { logout() }
        firebaseAuth = FirebaseAuth.getInstance()


        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.load_iamge_btn)
        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }

    private fun logout() {
        firebaseAuth.signOut()
            startActivity(Intent(this@LandingActivity,LoginActivity::class.java))

    }
}


