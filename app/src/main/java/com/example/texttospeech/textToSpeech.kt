package com.example.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.texttospeech.databinding.FragmentTextToSpeechBinding
import java.util.Locale

class TextToSpeechFragment : Fragment(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private lateinit var binding: FragmentTextToSpeechBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTextToSpeechBinding.inflate(inflater, container, false)

        // Initialize TextToSpeech
        tts = TextToSpeech(requireContext(), this)

        // Set up button click listener
        binding.btnSpeak.setOnClickListener {
            speakOut()
        }

        return binding.root
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            } else {
                binding.btnSpeak.isEnabled = true
            }
        } else {
            Log.e("TTS", "Initialization failed!")
        }
    }

    private fun speakOut() {
        val text = binding.etInput.text.toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        // Shutdown TTS when the fragment is destroyed
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}
