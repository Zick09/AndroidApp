package com.example.secondapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Locale;



public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private Button mSpeakButton;
    private Button mStopButton;
    private TextView enText;
    private TextView ruText;
    private TextView statusText;
    private Dictionary<String, String[]> dict = new Hashtable<>();
    private Locale localeRu;
    private Locale localeEn;
    private boolean stoped;

    SpeakThread thread;
    private TextToSpeech mTextToSpeech;
    private boolean mIsInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enText = findViewById(R.id.enText);
        ruText = findViewById(R.id.ruText);
        statusText = findViewById(R.id.statusText);
        mSpeakButton = findViewById(R.id.buttonSpeak);
        mStopButton = findViewById(R.id.buttonStop);
        mTextToSpeech = new TextToSpeech(this, this);
        localeRu = new Locale("ru");
        localeEn = new Locale("en");
        stoped = true;

        dict.put("en",new String[]

                {
                        "Furthermore", "Pursuit", "Last", "Rarely", "Although", "Strictly", "Outfit", "Tissue", "Mention", "Strict", "Harbor",
                        "Starring", "Noble", "Glory", "Reign", "Eternity", "Compose", "Circumstances", "Independent", "Entity", "Occurs", "Achivable", "Pageant", "Prosecution",
                        "Hut", "Cunt", "Solid", "Spread", "Wrecked", "Stumble", "Ownership", "Particulary", "Therefore", "Specify", "Invasion", "Infiltration", "Proposing",
                        "Rumors", "Envy", "Bounderies", "Seporate", "injection", "gratitude", "gratful", "ungrateful", "Rather", "Gizmo", "Apperantly", "selfish", "Reveal",
                        "Enhanced", "Contempt", "Torment", "Hardships", "Vain", "Encounters", "Besmirch", "Boilerplate", "Condoles", "Symphaty", "shove", "climaxed", "snapped off",
                        "ashes", "nod", "tongue", "disctract", "Chart", "Despise", "Displase", "arrogant", "Reliable", "coincidence", "Рrime", "Nasty", "Endangerment", "Deliberate",
                        "Endure", "Infatuation", "Encourage", "Contribute", "Convoluted", "Consistensy", "Pace", "Tucked", "Obtain", "Spank", "Upstanding", "Congenial",
                        "Intentionally", "Hints", "Tempting", "Indulgences", "Restrain", "Expose", "Excesiary", "Exclude", "Enclose", "Insulate",
                        "if you're attached to that hand", "infant", "rose from the dead", "gotta stay put", "intended", "rebel", "cut the shit",
                        "petulant", "needless to say", "smirk", "whinge", "moan", "sincerity", "raped", "tagged", "terminated", "Insult", "Fluff", "To fluff", "Vow", "Be adviced",
                        "Tweaks", "Effort", "Faintest idea", "Narrow", "Consent", "Lean on", "Diabolically", "Suit yourself", "Load of Bollocks", "Inocent", "Coup", "Hopitable",
                        "Stinks", "Interaction", "Eventually", "Provoking", "Playthrough", "Standpoint", "Reevaluate", "About to be"
                });
        dict.put("ru",new String[]

                {
                        "более того", "преследование", "длится, последний", "редко", "хотя", "строго", "наряд, одежда", "салфетка",
                        "упомянуть", "строгий", "гавань", "в главной роли", "благородный", "слава", "слава", "вечность", "сочинить", "обстоятельства", "независимый", "сущность",
                        "происходит", "достижимо", "конкурс", "судебное преследование", "хижина", "пизда", "твердый, сплошной", "скатерть, покрывало", "разбитый, потерпевший",
                        "споткнулся", "владение", "особенно", "поэтому", "указывать", "вторжение", "проникновение", "предлогая", "слухи", "Завидовать", "границы", "разделение",
                        "Инъекция", "благодарность", "благодарный", "неблагодарный", "скорее", "штуковина", "видимо", "эгоистичный", "раскрывать", "увеличить, улучшить",
                        "презрение", "мучить", "трудности", "напрасный", "сталкиваться", "осквернять, опозорить", "шаблон", "соболезнования", "сочуствие", "засуну",
                        "достигло кульминации", "отломился", "пепел", "кивок", "язык", "отвлекать", "диаграмма, график", "презирать", "перемещение", "высовкомерный", "надежный",
                        "совпадение", "основной", "противный", "угроза", "преднамеренный", "терпеть", "увлечение", "поощрять", "способствовать, внести вклад", "запутанный",
                        "последовательность", "темп, шаг", "заправленный, поджатый", "получить", "шлепать", "порядочный", "благоприятный, близкий по духу", "намеренно",
                        "намеки", "заманчиво", "снисхождение, поблажки, ", "сдерживать", "разоблачать", "чрезмерный", "исключение", "ограждение", "изоляция",
                        "если ты привязан к этой руке", "младенец", "воскрес из мертвых", "должен оставаться на месте", "намеревался", "мятежник", "прекрати это дерьмо",
                        "раздражительный", "разумеется, не нужно говорить", "ухмылка", "скулить, ныть", "стон", "искренность", "изнасиловал", "отмеченный", "прекращено",
                        "оскорблять", "пух", "взбивать", "клятва", "иметь ввиду", "настройки", "усилие", "слабая идея", "узкий", "согласие", "опереться", "дьявольщина",
                        "устраивайся", "чушь", "невиновный", "переворот", "гостеприимный", "воняет", "взаимодействие", "в конце концов", "провоцирующий", "прохождение игры",
                        "точка зрения", "переоценить (повторно)", "собираюсь быть"
                });
        mStopButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (thread.isAlive()){
                    thread.interrupt();
                    stoped = true;
                }
            }
        });
        mSpeakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!stoped){
                    thread.interrupt();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    thread = new SpeakThread(dict, localeRu, localeEn, mTextToSpeech, enText, ruText);
                    thread.start();
                } else if (stoped){
                    thread = new SpeakThread(dict, localeRu, localeEn, mTextToSpeech, enText, ruText);
                    thread.start();
                    stoped = false;
                }
                statusText.setText(thread.getName());
            }
        });

    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Locale localeRu = new Locale("ru");
            Locale localeEn = new Locale("en");
            int resultRu = mTextToSpeech.setLanguage(localeRu);
            int resultEn = mTextToSpeech.setLanguage(localeEn);
            if (resultRu == TextToSpeech.LANG_MISSING_DATA || resultRu == TextToSpeech.LANG_NOT_SUPPORTED) {
                mIsInit = false;
            } else if  (resultEn == TextToSpeech.LANG_MISSING_DATA || resultEn == TextToSpeech.LANG_NOT_SUPPORTED){
                mIsInit = false;
            } else {
                mIsInit = true;
            }
        } else {
            mIsInit = false;
        }
    }
}

