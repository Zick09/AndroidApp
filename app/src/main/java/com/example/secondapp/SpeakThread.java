package com.example.secondapp;

import android.speech.tts.TextToSpeech;

import java.util.Dictionary;
import java.util.Locale;

public class SpeakThread extends Thread{
    private Locale localeRu;
    private Locale localeEn ;
    private Dictionary<String, String[]> dict;
    private TextToSpeech mTextToSpeech;

    public SpeakThread(Dictionary<String, String[]> dict, Locale localeRu, Locale localeEn, TextToSpeech mTextToSpeech){
        this.dict = dict;
        this.localeEn = localeEn;
        this.localeRu = localeRu;
        this.mTextToSpeech = mTextToSpeech;
    }

    @Override
    public void run(){
        for (int i = 0; i < dict.get("en").length; i++) {

            mTextToSpeech.setLanguage(localeEn);
            mTextToSpeech.speak(dict.get("en")[i], TextToSpeech.QUEUE_FLUSH, null, null);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mTextToSpeech.setLanguage(localeRu);
            mTextToSpeech.speak(dict.get("ru")[i], TextToSpeech.QUEUE_FLUSH, null, null);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
