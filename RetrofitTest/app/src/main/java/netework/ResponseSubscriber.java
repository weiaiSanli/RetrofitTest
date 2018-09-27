package netework;

import io.reactivex.observers.ResourceObserver;

public abstract class ResponseSubscriber<T> extends ResourceObserver<T> {



    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFailure(e);
    }

    @Override
    public void onNext(T t) {

            onSuccess(t);

    }



    public abstract void onFailure(Throwable e);

    public abstract void onSuccess(T t);

}
