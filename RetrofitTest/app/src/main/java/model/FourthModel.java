package model;

import javax.inject.Inject;

import contract.FourthContract;

public class FourthModel implements FourthContract.Model {

    @Inject
    public FourthModel() {
    }


    @Override
    public void loginNet(String userName, String pwd, Object o) {
        System.out.println("userName" + userName);
    }
}
