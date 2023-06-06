package webservice;

import java.util.List;

import models.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {

    @GET("{user}")
    public Call<Users> getUser(@Path("user") String user);

    @GET("{bio}/{name}")
    public Call<List<Users>> getLogin(@Path("bio") String biografia,
                                      @Path("name") String nome);
}
