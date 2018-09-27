package presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.uber.autodispose.AutoDisposeConverter;

import org.jetbrains.annotations.NotNull;

import water.retrofittest.autodisponse.RxLifecycleUtils;

/**
 * 用于改善后的标准的mvp,在module层请求数据
 */
public abstract class MvpBasePresenter<V,T> implements IPresenter  {

    protected T mvpModule;
    protected V mvpView;

    private String TAG = "MvpBasePresenter";

    private LifecycleOwner lifecycleOwner ;

    //创建view
    public void attachView(V mvpView) {
        this.mvpView = mvpView ;
        mvpModule = creatModule();
    }

    //给与子类实现创建module的实例
    protected abstract T creatModule();


    /**
     * 绑定对象
     * @param <T>
     * @return
     */
    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == lifecycleOwner)
            throw new NullPointerException("lifecycleOwner == null");
        return RxLifecycleUtils.bindLifecycle(lifecycleOwner);


    }

    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {
        this.lifecycleOwner = owner ;
    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        if (mvpModule != null) {
//            mvpModule.onDestroy();
            this.mvpModule = null;
        }
        this.mvpView = null ;
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

        Log.i(TAG, "onLifecycleChanged: ");
    }


}