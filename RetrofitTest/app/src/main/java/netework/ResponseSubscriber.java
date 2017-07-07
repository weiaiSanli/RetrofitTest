package netework;

import rx.Subscriber;

public abstract class ResponseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

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
