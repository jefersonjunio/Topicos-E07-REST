package webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubConfig {

    private Retrofit retrofit;

    public GitHubConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public UserService getUserService(){
        return this.retrofit.create(UserService.class);
    }
}
