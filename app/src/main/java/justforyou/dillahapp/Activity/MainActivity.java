package justforyou.dillahapp.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import justforyou.dillahapp.ProfileActivity;
import justforyou.dillahapp.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fabVoice)
    ImageView fabVoice;

    @BindView(R.id.txtInfo)
    TextView txtInfo;


    private static final int REQUEST_CODE = 1234;

    boolean status = false;
    private TextToSpeech mTTS;
    private MediaPlayer mpintro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        checkPermission();

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0) {
            fabVoice.setEnabled(false);

        }

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());


                  mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {
               // Toast.makeText(MainActivity.this,"rms",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null) {
                    if (status) {

                        /*if (matches.get(0).equals("thanks")) {
                            status = false;
                            speak("Thanks, see you again!");
                            finish();
                        }
                        if (matches.get(0).equals("Buka jadwal kuliah")) {
                            status = true;
                            speak("Instruction, Accepted!!");
                            openKuliah();
                        }
                        if (matches.get(0).equals("Di mana aku sekarang")) {
                            status = true;
                            speak("Instruction, Accepted!!");
                            startActivity(new Intent(MainActivity.this, MapsActivity.class));
                        }
                        if (matches.get(0).equals("siapa aku")) {
                            status = true;
                            speak("you are is very beautiful girl ");
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        }
                        if (matches.get(0).equals("Siapa kamu")) {
                            status = true;
                            speak("I am artificial intelligence made by Mr. Eko");
                        }
                        if (matches.get(0).equals("play music")) {
                             status = true;
                            speak("Instruction, Accepted!!");

                        }
                    } else {
                        speak("Access Denied!");
                        txtInfo.setText("Intruction, Rejected!");
                    }

                    if (matches.get(0).equals("morning")) {
                        status = true;
                        speak("Hello miss, Welcome back!");
                    }
                    if (matches.get(0).equals("eh dia bangun")) {

                        speak("uh sheeee up!");
                    }*/

                        if (matches.get(0).indexOf("thank") != -1) {
                            status = false;
                            speak("Thanks, see you again!");
                            finish();
                        } else if (matches.get(0).indexOf("Open") != -1) {
                            status = true;
                            speak("Instruction, Accepted!!");
                            if (matches.get(0).indexOf("schedule") != -1) {
                                openKuliah();
                            }
                        } else if (matches.get(0).indexOf("Where") != -1) {
                            status = true;
                            if (matches.get(0).indexOf("you") != -1) {
                                speak("Instruction, Accepted!!");
                                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                            }

                        }  else if (matches.get(0).equals("play music")) {
                            status = true;
                            speak("Instruction, Accepted!!");
                        }
                    } else {
                        speak("Access Denied!");
                        txtInfo.setText("Intruction, Rejected!");
                    }

                    if (matches.get(0).indexOf("friday") != -1 || matches.get(0).indexOf("Friday") != -1 ) {
                        status = true;
                        speak("Hello sir, Welcome back!");
                    }
                    if (matches.get(0).indexOf("halo") != -1 || matches.get(0).indexOf("hello") != -1){
                        speak("Hello, Who are you!");
                    }

                    txtInfo.setText(matches.get(0));
                    //mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                }



            }

            @Override
            public void onPartialResults(Bundle bundle) {
                Toast.makeText(MainActivity.this,"partial",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
                Toast.makeText(MainActivity.this,"event",Toast.LENGTH_SHORT).show();
            }
        });

       findViewById(R.id.fabVoice).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();
                        txtInfo.setText("You will see input here");
                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        txtInfo.setText("Listening...");
                        break;
                }
                return false;
            }
        });
        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    //  new Locale("id","ID")
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } /*else {
                        mButtonSpeak.setEnabled(true);
                    }*/
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


    }

//    @OnClick(R.id.fabVoice)
//    public void googleVoiceOpen() {
//        startVoiceRecognitionActivity();
//    }

    public void speakButtonClicked(View v) {
        startVoiceRecognitionActivity();
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice searching...");
        startActivityForResult(intent, REQUEST_CODE);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            // Populate the wordsList with the String values the recognition engine thought it heard
            final ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (!matches.isEmpty()) {
                if (status) {

                    if (matches.get(0).indexOf("thank") != -1) {
                        status = false;
                        speak("Thanks, see you again!");
                        finish();
                    } else if (matches.get(0).indexOf("Open") != -1) {
                        status = true;
                        speak("Instruction, Accepted!!");
                        if (matches.get(0).indexOf("schedule") != -1) {
                            openKuliah();
                        }
                    } else if (matches.get(0).indexOf("Where") != -1) {
                        status = true;
                        if (matches.get(0).indexOf("you") != -1) {
                            speak("Instruction, Accepted!!");
                            startActivity(new Intent(MainActivity.this, MapsActivity.class));
                        }
                        if (matches.get(0).indexOf("I am") != -1) {
                            speak("in my heart!");
                        }

                    } else if (matches.get(0).indexOf("Who") != -1) {
                        status = true;
                        if (matches.get(0).indexOf("you") != -1) {
                            speak("I am is, artificial intelligence Assistant System!");
                        } else if (matches.get(0).indexOf("I am") != -1) {
                            speak("you are is very beautiful girl ");
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        }
                    } else if (matches.get(0).equals("play music")) {
                        status = true;
                        speak("Instruction, Accepted!!");
                    }
                } else {
                    speak("Access Denied!");
                    txtInfo.setText("Intruction, Rejected!");
                }

                if (matches.get(0).indexOf("morning") != -1) {
                    status = true;
                    speak("Hello miss, Welcome back!");
                }
                if (matches.get(0).indexOf("halo") != -1 || matches.get(0).indexOf("hello") != -1){
                    speak("Hello, Who are you!");
                }

                txtInfo.setText(matches.get(0));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    private void speak(String speak) {
        String text = speak;
        float pitch = (float) 0.9;
       //  if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) 0.9;
       //  if (speed < 0.1) speed = 0.1f;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @OnClick(R.id.btnKuliah)
    public void openKuliah() {
        startActivity(new Intent(MainActivity.this, KuliahActivity.class));
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }

}
