package pinkjacket.chickentonight;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class YumService {
    public static void findRecipes(String search, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YUMMLY_BASE_SEARCH_TERM, search);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("X-Yummly-App-ID", Constants.YUMMLY_ID)
                .header("X-Yummly-App-Key", Constants.YUMMLY_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
