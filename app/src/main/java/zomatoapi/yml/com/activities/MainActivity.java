package zomatoapi.yml.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zomatoapi.yml.com.adapter.RestaurentCategoryAdapter;
import zomatoapi.yml.com.interfaces.ApiInterface;
import zomatoapi.yml.com.models.RestaurentCategory;
import zomatoapi.yml.com.retrofit.ApiClient;
import zomatoapi.yml.com.zomatoapi.R;

public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView mRecyclerView;
    private Subscription subscription;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mProgressBar = findViewById(R.id.progress_bar);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Single<RestaurentCategory> restaurentCategoryObservable = apiInterface.getCategories("333887d4420e1e8886f3ebaf9f88c64c");

//        restaurentCategoryObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.
//                mainThread()).subscribe(new SingleObserver<RestaurentCategory>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(RestaurentCategory restaurentCategory) {
//                if (restaurentCategory != null) {
//                    mProgressBar.setVisibility(View.GONE);
//                    showToast("response not null");
//                    RestaurentCategory restaurentCategoryDetails = restaurentCategory;
//                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                    RestaurentCategoryAdapter adapter = new RestaurentCategoryAdapter(restaurentCategoryDetails);
//                    mRecyclerView.setAdapter(adapter);
//
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                showToast(e.getMessage());
//            }
//        });
//        mProgressBar.setVisibility(View.VISIBLE);
//
//
        restaurentCategoryObservable.compose(applySchedulers).subscribe(singleObserver);
//        apiInterface.getRestaurants("333887d4420e1e8886f3ebaf9f88c64c", 16774318).compose(applySchedulers).subscribe(singleObserver);

    }

    private SingleTransformer<RestaurentCategory, RestaurentCategory> applySchedulers = new SingleTransformer<RestaurentCategory, RestaurentCategory>() {
        @Override
        public Single<RestaurentCategory> apply(Single<RestaurentCategory> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        }
    };

    private SingleObserver singleObserver = new SingleObserver() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onSuccess(Object restaurentCategory) {
            if (restaurentCategory != null) {
                mProgressBar.setVisibility(View.GONE);
                showToast("response not null");
                RestaurentCategory restaurentCategoryDetails = (RestaurentCategory) restaurentCategory;
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                RestaurentCategoryAdapter adapter = new RestaurentCategoryAdapter(restaurentCategoryDetails);
                mRecyclerView.setAdapter(adapter);

            }
        }

        @Override
        public void onError(Throwable e) {

        }
    };

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
