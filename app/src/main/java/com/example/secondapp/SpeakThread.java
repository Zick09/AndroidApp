package com.example.secondapp;

import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Locale;

public class SpeakThread extends Thread{
    private Locale localeRu;
    private Locale localeEn ;
    private Dictionary<String, String[]> dict;
    private TextToSpeech mTextToSpeech;

    private TextView enText;
    private TextView ruText;

    public SpeakThread(Dictionary<String, String[]> dict, Locale localeRu, Locale localeEn, TextToSpeech mTextToSpeech, TextView enText, TextView ruText){
        this.dict = dict;
            this.localeEn = localeEn;
            this.localeRu = localeRu;
            this.mTextToSpeech = mTextToSpeech;

            this.enText = enText;
            this.ruText = ruText;

    }


    @Override
    public void run(){
        for (int i = 0; i < dict.get("en").length; i++) {
//                    enText.setText(Thread.currentThread().getName());
            enText.setText(dict.get("en")[i]);
            ruText.setText(dict.get("ru")[i]);
            mTextToSpeech.setLanguage(localeEn);
//                    enText.setBackgroundColor();
            mTextToSpeech.speak(dict.get("en")[i], TextToSpeech.QUEUE_FLUSH, null, null);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            mTextToSpeech.setLanguage(localeRu);
            mTextToSpeech.speak(dict.get("ru")[i], TextToSpeech.QUEUE_FLUSH, null, null);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
