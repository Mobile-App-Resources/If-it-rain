package root.if_it_rains.Connect;

        import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by geni on 2017. 8. 31..
 */

public class RetrofitClass {
    private static RetrofitClass retrofitClass = new RetrofitClass();

    private Retrofit retrofit;

    public ApiInterface apiInterface;

    private RetrofitClass(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://13.124.230.198:8027")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public static RetrofitClass getInstance() {
        return retrofitClass;
    }
}
