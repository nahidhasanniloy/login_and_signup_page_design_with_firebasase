package com.example.finalproject

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.BoringLayout
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class Login_Fragment : Fragment() {
    lateinit var binding: FragmentLoginBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.btnLogin.setOnClickListener{
             val email = binding.etEmailAddress.text.toString().trim()
            val password  = binding.etPassword.text.toString().trim()
                    if( isEmailvalid(email)  && isPasswordValid(password)){

                             loginUser(email,password)

                    }
                    else{
                        Toast.makeText(requireContext(), " invalid email or password ", Toast.LENGTH_SHORT).show()
                    }





        }

            binding.btnSignup.setOnClickListener{
                findNavController().navigate(R.id.action_login_Fragment_to_signUp_Fragment2)

            }



        return binding.root
    }

    private fun loginUser(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(requireContext(), "Login successful: ${user?.email}", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_login_Fragment_to_home_Fragment)
            } else {
                Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun isEmailvalid( email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
    private fun isPasswordValid(password: String): Boolean {
        val passregex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$")
        return password.matches(passregex)
    }


}