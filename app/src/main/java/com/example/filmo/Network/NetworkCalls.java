//package com.example.filmo.Network;
//
//import android.util.Log;
//
//import com.example.filmo.Model.movies.Result;
//
//import java.util.List;
//
//import androidx.annotation.NonNull;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class NetworkCalls {
//    private List<Result> nowPlayingMoviesList;
//    private List<Result> topRatedMoviesList;
//    private List<Result> upComingMoviesList;
//
//    public List<Result> generateTopRatedCall() {
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<Result>> call = service.getTopRatedMovies();
//        call.enqueue(new Callback<List<Result>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<Result>> call, @NonNull Response<List<Result>> response) {
//                topRatedMoviesList = (List<Result>) response.body();
//                Log.v("TopRated Call", response.message().toString());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<Result>> call, @NonNull Throwable t) {
//                Log.v("TopRated Call", t.getMessage());
//            }
//        });
//        return topRatedMoviesList;
//    }
//
//    public List<Result> generateUpComingCall() {
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<Result>> call = service.getUpComingMovies();
//        call.enqueue(new Callback<List<Result>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<Result>> call, @NonNull Response<List<Result>> response) {
//                upComingMoviesList = (List<Result>) response.body();
//                Log.v("UpComing Call", response.message().toString());
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<List<Result>> call, @NonNull Throwable t) {
//                Log.v("UpComing Call", t.getMessage());
//            }
//        });
//        return upComingMoviesList;
//    }
////
////    public List<Result> generateNowPlayingCall() {
////        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
////        Call<List<Result>> call = service.getNowPlayingMovies();
////        call.enqueue(new Callback<List<Result>>() {
////            @Override
////            public void onResponse(@NonNull Call<List<Result>> call, @NonNull Response<List<Result>> response) {
////                nowPlayingMoviesList = (List<Result>) response.body();
////                Log.v("NowPlaying Call", response.message().toString());
////            }
////
////            @Override
////            public void onFailure(@NonNull Call<List<Result>> call, @NonNull Throwable t) {
////                Log.v("NowPlaying Call", t.getMessage());
////            }
////        });
////        return nowPlayingMoviesList;
////    }
//
//}
