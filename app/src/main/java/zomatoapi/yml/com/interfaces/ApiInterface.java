package zomatoapi.yml.com.interfaces;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import zomatoapi.yml.com.models.RestaurentCategory;

public interface ApiInterface {
//    @GET("categories")
//    Call<RestaurentCategory> getCategories(@Header("user-key") String key);

    @GET("categories")
    Single<RestaurentCategory> getCategories(@Header("user-key") String key);

    @GET("restaurant")
    Single<RestaurentCategory> getRestaurants(@Header("user-key") String key, @Query("res-id") Integer resId);
}
