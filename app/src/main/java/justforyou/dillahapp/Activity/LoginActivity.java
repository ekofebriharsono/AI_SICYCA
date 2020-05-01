package justforyou.dillahapp.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import justforyou.dillahapp.Api.UserClient;
import justforyou.dillahapp.Config.ApiConfig;
import justforyou.dillahapp.R;
import justforyou.dillahapp.Result.Login;
import justforyou.dillahapp.Result.Message;
import justforyou.dillahapp.Result.ResultDataUmum;
import justforyou.dillahapp.Result.UpdateFCM;
import justforyou.dillahapp.Result.User;
import justforyou.dillahapp.SharedPreferences.SaveSharedPreference;
import justforyou.dillahapp.Utils.ValidationUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.EdNIm)
    EditText nim;

    @BindView(R.id.EdPin)
    EditText pin;

    @BindView(R.id.checkboxIngat)
    CheckBox checkboxIngat;

    private List<ResultDataUmum> results = new ArrayList<>();
    private String token;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Locale id =  new Locale("id","ID");
                    int result = mTTS.setLanguage(id);

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

        if (SaveSharedPreference.getIngat(LoginActivity.this)) {
            checkboxIngat.setChecked(true);
            nim.setText(SaveSharedPreference.getNim(LoginActivity.this));
            pin.setText(SaveSharedPreference.getPin(LoginActivity.this));
        } else {
            checkboxIngat.setChecked(false);
        }
    }

    public void login(String nim, final String pin) {

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Checking User...");
        dialog.setCancelable(false);
        dialog.show();

        final Login login = new Login(nim, pin);

        UserClient getResponse = ApiConfig.getRetrofit().create(UserClient.class);
        Call<User> call = getResponse.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getNim().equals("16410100003")){
                        speak("Mohon Maaf "+ response.body().getNama() + ", kamu bukan siapa siapa!");
                        dialog.hide();
                    } else {
                        token = "Bearer " + response.body().getMeta().getToken();
                        UpdateFCM(token);
                        resetField();
                        dialog.hide();
                        SaveSharedPreference.setNim(LoginActivity.this, response.body().getNim());
                        SaveSharedPreference.setNama(LoginActivity.this, response.body().getNama());
                        SaveSharedPreference.setToken(LoginActivity.this, response.body().getMeta().getToken());
                        SaveSharedPreference.setFoto(LoginActivity.this, response.body().getPhoto());
                        SaveSharedPreference.setPin(LoginActivity.this, pin);
                        SaveSharedPreference.setIngat(LoginActivity.this, true);
                        SaveSharedPreference.setEmail(LoginActivity.this, response.body().getEmail());
                        SaveSharedPreference.setJk(LoginActivity.this, response.body().getJenisKelamin());
                        SaveSharedPreference.setTtl(LoginActivity.this, response.body().getTtl());
                        SaveSharedPreference.setAgama(LoginActivity.this, response.body().getAgama());
                        SaveSharedPreference.setTelp(LoginActivity.this, response.body().getTelp());
                        SaveSharedPreference.setDs(LoginActivity.this, response.body().getDosenWali());
                        finish();
                        Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                } else {
                    dialog.hide();
                    Toast.makeText(LoginActivity.this, "Username or Password Invalid!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                dialog.hide();
                Toast.makeText(LoginActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void speak(String speak) {
        String text = speak;
        float pitch = (float) 1;
        // if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) 1;
        // if (speed < 0.1) speed = 0.1f;

        mTTS.setPitch(pitch);
        mTTS.setSpeechRate(speed);

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void UpdateFCM(String token) {
        UpdateFCM fcm = new UpdateFCM("fcm_regsis_id");

        UserClient getResponse = ApiConfig.getRetrofit().create(UserClient.class);
        Call<Message> call = getResponse.getSecret(token, fcm);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()) {
                    // Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Token not Corret", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.btnSubmit)
    public void submit() {
        if (fieldsValidation()) {
            login(nim.getText().toString(), pin.getText().toString());
        } else {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean fieldsValidation() {
        return !ValidationUtils.isEmpty(nim) &&
                !ValidationUtils.isEmpty(pin);
    }

    private void resetField() {
        nim.setText("");
        pin.setText("");
    }
}
