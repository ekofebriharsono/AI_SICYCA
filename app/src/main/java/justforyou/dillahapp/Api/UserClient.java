package justforyou.dillahapp.Api;

import justforyou.dillahapp.Result.Login;
import justforyou.dillahapp.Result.Message;
import justforyou.dillahapp.Result.ResultDataUmum;
import justforyou.dillahapp.Result.UpdateFCM;
import justforyou.dillahapp.Result.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserClient {
    @POST("api/auth/login")
    Call<User> login(@Body Login login);


    @Headers("Content-Type: application/json")
    @PUT("api/update-fcm")
    Call<Message> getSecret(@Header("Authorization") String Authorization,
                            @Body UpdateFCM fcm);

    @GET("api/jadwal-hari-ini")
    Call<ResultDataUmum> getJadwalHariIni(@Header("Authorization") String Authorization);

    @GET("api/jadwal-mingguan")
    Call<ResultDataUmum> getJadwalMingguIni(@Header("Authorization") String Authorization);

    @GET("api/jadwal-uts")
    Call<ResultDataUmum> getJadwalUTS(@Header("Authorization") String Authorization);

    @GET("api/jadwal-uas")
    Call<ResultDataUmum> getJadwalUAS(@Header("Authorization") String Authorization);

//    @GET("api/krs")
//    Call<ResultDataKRS> getDataKRS(@Header("Authorization") String Authorization);
}
