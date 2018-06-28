package presenter;

import javax.inject.Inject;

import contract.FourthContract;

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

        model.loginNet("nihao" , "heihei" , null);

    }
}
