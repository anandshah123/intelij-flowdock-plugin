package com.github.intelij.flowdock.plugin.api;

import com.github.intelij.flowdock.plugin.api.dto.Flow;
import com.github.intelij.flowdock.plugin.api.dto.Message;
import com.github.intelij.flowdock.plugin.util.ApplicationConstants;
import com.intellij.ide.util.PropertiesComponent;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public interface FlowdockAPI {

    static FlowdockAPI getInstance() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();


            String credentials = PropertiesComponent.getInstance().getValue(ApplicationConstants.FLOWDOCK_API_USERNAME) + ":" + PropertiesComponent.getInstance().getValue(ApplicationConstants.FLOWDOCK_API_PASSWORD);
            final String basic =
                    "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", basic)
                    .header("Accept", "application/json")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }).build();

        return new Retrofit.Builder()
                .baseUrl("https://api.flowdock.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build().create(FlowdockAPI.class);
    }

    static boolean checkCredentials() throws IOException {
        return getInstance().getFlows(0).execute().code() == 200;
    }

    @GET("flows/")
    Call<List<Flow>> getFlows(@Query("users") int users);

    @GET("flows/{organization}/{flow}/messages")
    Call<List<Message>> getMessages(@Path("organization") String organization, @Path("flow") String flow, @Query("search") String search, @Query("event") String event);

    @POST("flows/{organization}/{flow}/messages")
    Call<Response> postMessage(@Path("organization") String organization, @Path("flow") String flow, @Body Message message);

    @DELETE("flows/{organization}/{flow}/messages/{id}")
    Call<Response> deleteMessage(@Path("organization") String organization, @Path("flow") String flow, @Path("id") String id);

    @POST("private/{user}/messages")
    Call<Response> postPrivateMessage(@Path("user") String userId, @Body Message message);

    @GET("private/{user}/messages")
    Call<List<Message>> getPrivateMessage(@Path("user") String user, @Query("search") String search, @Query("event") String event);

}
