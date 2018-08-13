package presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

public class BasePresenter<V> implements LifecycleObserver{

    protected V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView ;


    }

    public void detachView() {
        this.mvpView = null ;
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        System.out.println("当期是 onCreate()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        System.out.println("当期是 onResume()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        System.out.println("当前是 onPause()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        System.out.println("当前是 onPause()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        System.out.println("当前是 onPause()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        System.out.println("当前是 onPause()");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY) //只要有Even状态的更改就会调用此方法
    public void onAny() {
//        System.out.println("当前是 onAny()");
    }





}
