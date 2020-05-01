package justforyou.dillahapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import justforyou.dillahapp.SharedPreferences.SaveSharedPreference;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.Nim)
    TextView Nim;

    @BindView(R.id.Nama)
    TextView Nama;

    @BindView(R.id.Email)
    TextView Email;

    @BindView(R.id.JenisKelamin)
    TextView JenisKelamin;

    @BindView(R.id.Ttl)
    TextView Ttl;

    @BindView(R.id.Agama)
    TextView Agama;

    @BindView(R.id.Telp)
    TextView Telp;

    @BindView(R.id.DosenWali)
    TextView DosenWali;

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        Nim.setText(SaveSharedPreference.getNim(ProfileActivity.this));
        Nama.setText(SaveSharedPreference.getNama(ProfileActivity.this));
        Email.setText(SaveSharedPreference.getEmail(ProfileActivity.this));
        JenisKelamin.setText(SaveSharedPreference.getJk(ProfileActivity.this));
        Ttl.setText(SaveSharedPreference.getTtl(ProfileActivity.this));
        Agama.setText(SaveSharedPreference.getAgama(ProfileActivity.this));
        Telp.setText(SaveSharedPreference.getTelp(ProfileActivity.this));
        DosenWali.setText(SaveSharedPreference.getDs(ProfileActivity.this));

        Picasso.with(ProfileActivity.this)
                .load(SaveSharedPreference.getFoto(ProfileActivity.this))
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(imageView);
    }
}
