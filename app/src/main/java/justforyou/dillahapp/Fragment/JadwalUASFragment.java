package justforyou.dillahapp.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import justforyou.dillahapp.Adapter.JadwalKuliahAdapter;
import justforyou.dillahapp.Api.UserClient;
import justforyou.dillahapp.Config.ApiConfig;
import justforyou.dillahapp.Model.DataUmum;
import justforyou.dillahapp.R;
import justforyou.dillahapp.Result.ResultDataUmum;
import justforyou.dillahapp.SharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class JadwalUASFragment extends Fragment {
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private List<DataUmum> results = new ArrayList<>();
    private JadwalKuliahAdapter jadwalKuliahAdapter;

    Activity activity;

    @SuppressLint("ValidFragment")
    public JadwalUASFragment(Activity activity) {
        // Required empty public constructor
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal_uas, container, false);
        ButterKnife.bind(this, view);

        jadwalKuliahAdapter = new JadwalKuliahAdapter(getContext(), results, true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(jadwalKuliahAdapter);

        String token = "Bearer " + SaveSharedPreference.getToken(activity);

        loadJadwalUAS(token);

        return view;
    }

    public void loadJadwalUAS(final String token){

        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        UserClient getResponse = ApiConfig.getRetrofit().create(UserClient.class);
        Call<ResultDataUmum> call = getResponse.getJadwalUAS(token);

        call.enqueue(new Callback<ResultDataUmum>() {
            @Override
            public void onResponse(Call<ResultDataUmum> call, Response<ResultDataUmum> response) {
                if (response.isSuccessful()) {
                    dialog.hide();
                    results = response.body().getData();
                    jadwalKuliahAdapter = new JadwalKuliahAdapter(getContext(), results, true);
                    recyclerView.setAdapter(jadwalKuliahAdapter);
                } else {
                    dialog.hide();
                    Toast.makeText(getContext(), "Jadwal Not Found!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), token, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultDataUmum> call, Throwable t) {
                dialog.hide();
                Toast.makeText(getContext(), "Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
