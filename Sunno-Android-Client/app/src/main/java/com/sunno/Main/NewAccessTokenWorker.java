package com.sunno.Main;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.sunno.AuthModule.db.LoginDao;
import com.sunno.AuthModule.db.LoginDatabase;
import com.sunno.AuthModule.network.LoginEndpoints;
import com.sunno.AuthModule.network.LoginService;
import com.sunno.AuthModule.network.request.RefreshTokenRequest;
import com.sunno.Main.Model.Repository;
import com.sunno.Main.network.ApiEndpoint;
import com.sunno.Main.network.ApiService;
import com.sunno.Main.network.NetworkEndpoint;
import com.sunno.Main.network.NetworkService;

public class NewAccessTokenWorker extends Worker {

    Application application;

    public NewAccessTokenWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @NonNull
    @Override
    public Result doWork() {
        if(application!=null){
        Repository repository = Repository.getInstance(application);
        repository.getNewAccessToken();
        }
        return Result.success();
    }
}
