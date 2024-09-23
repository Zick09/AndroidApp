package com.example.secondapp;

import android.speech.tts.TextToSpeech;

public class SpeakClass extends Thread{
    private TextToSpeech mTextToSpeech;
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
