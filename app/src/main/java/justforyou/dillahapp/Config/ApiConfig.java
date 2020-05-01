package justforyou.dillahapp.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    private static String BASE_URL = "http://api.sicyca.stikom.edu/";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}