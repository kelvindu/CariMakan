package com.kelvindu.learning.carimakan.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

/**
 * The base activity class that stores basic methods that can be used by all of the activity class.
 *
 * Created by KD on 5/21/2017.
 */

public class BaseActivity extends AppCompatActivity {

    //this app uses reactive disposable that will handles all of the request,
    // so it might be useful if an activity can have it directly without declaring this too many times.
    protected Disposable disposable = new CompositeDisposable();

    /**
     * This method does that all redundant lines that you used to bind views, get realm config, and setting the content view.
     *
     * @param layout is the id of the activity, used to set the context and the content view.
     * */
    protected void bind(int layout){
        setContentView(layout);
        ButterKnife.bind(this);
        Realm.init(getApplicationContext());
    }

    /**
     * This method tells user information of the app/bugs in the found app.
     *
     * @param message is the message that is read by users. regarding something.
     * */
    protected void ShowToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * on destroy will likely can be the same on every activity codes so why not declare we declare it as base activity?
     *
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    /**
     * This method is just to instantiate an activity where you don't have any intent stored.
     *
     * */
    protected void openNewActivity(Class activity){
        startActivity(new Intent(this, activity));
        finish();
    }
}
