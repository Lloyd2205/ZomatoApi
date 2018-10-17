package zomatoapi.yml.com.interfaces;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import zomatoapi.yml.com.models.RestaurentCategory;

public interface ApiInterface {
//    @GET("categories")
//    Call<RestaurentCategory> getCategories(@Header("user-key") String key);

    @GET("categories")
    Single<RestaurentCategory> getCategories(@Header("user-key") String key);
}
