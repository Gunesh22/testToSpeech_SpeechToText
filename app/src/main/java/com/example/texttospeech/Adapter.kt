package Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.texttospeech.SpeechToTextFragment
import com.example.texttospeech.TextToSpeechFragment


class Adapter(FragmentActivity: FragmentActivity): FragmentStateAdapter(FragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TextToSpeechFragment()
            1 -> SpeechToTextFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

}