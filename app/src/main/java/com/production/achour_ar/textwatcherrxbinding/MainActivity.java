package com.production.achour_ar.textwatcherrxbinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.production.achour_ar.textwatcherrxbinding.databinding.ActivityMainBinding;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private Observable watcher;
    private Observable click;
    private Observer observerWatch;
    private Observer observerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        initObservables();
        initObservers();
        subscribe();

    }

    private void subscribe() {
        watcher.subscribe(observerWatch);
        click.subscribe(observerClick);
    }

    private void initObservables() {
        watcher = RxTextView.textChanges(binding.editText);
        click = RxView.clicks(binding.button);
    }

    private void initObservers() {
        observerWatch = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: watch - " + o.toString());
                binding.textView.setText(o.toString());
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };


        observerClick = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {}
            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: clicked !");
                binding.textView.setText("");
                binding.editText.setText("");
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };
    }
}
