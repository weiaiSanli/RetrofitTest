package presenter;

import javax.inject.Inject;

import contract.FourthContract;
import info.LoginNetInfo;

public class FourthPresenter implements FourthContract.Presenter {

    @Inject
    FourthContract.Model model ;

    private FourthContract.View mView ;
    @Inject
    public FourthPresenter(FourthContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loginNet() {



        model.loginNet(mView.getUserName(), mView.getPassWord(), new LoginNetInfo<String>() {
            @Override
            public void loginNetSuccess(String success) {
                mView.loginSuccess(success);
            }

            @Override
            public void loginNetError(String error) {

                mView.error(error);

            }
        });

    }
}
