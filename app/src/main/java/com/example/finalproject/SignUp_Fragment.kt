package com.example.finalproject

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.finalproject.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUp_Fragment : Fragment() {

lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)


           binding.btnSignup.setOnClickListener {
               val email = binding.etEmailAddress.text.toString().trim()
               val password  = binding.etPassword.text.toString().trim()
               val user = binding.etuser.text.toString().trim()

               if (isEmailvalid(email) && isPasswordValid(password)){

                   signupuser(email,password,user)

                       }else{
                   Toast.makeText(requireContext() , " Invalid email and password ", Toast.LENGTH_SHORT).show()
                       }





           }










        return  binding.root
    }

    private fun signupuser(email: String, password: String, user: String) {

        val auth = FirebaseAuth.getInstance()
         auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{  task ->

             if (task.isSuccessful){
                 Toast.makeText(requireContext() , "Create account successfull", Toast.LENGTH_SHORT).show()
                 findNavController().navigate(R.id.action_signUp_Fragment_to_home_Fragment)

             }else{

                 Toast.makeText(requireContext()," ${task.exception?.message} ", Toast.LENGTH_SHORT).show()
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