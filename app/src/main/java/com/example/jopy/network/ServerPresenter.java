package com.example.jopy.network;

import retrofit2.Callback;

public interface ServerPresenter<T,F> extends Callback<F> {
    void sendToServer(T request);


}
