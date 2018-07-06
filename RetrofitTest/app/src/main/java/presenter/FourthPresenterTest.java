package presenter;

import contract.FourthContract;
public class FourthPresenterTest implements FourthContract.Presenter {

    FourthContract.Model model ;
    FourthContract.View mView ;

    /**
     * Component会查找目标类中用Inject注解标注的属性，查找到相应的属性后会接着查找该属性对应的用Inject标注的构造函数
     * 因此我们也可以给Component叫另外一个名字注入器（Injector）
     */
    public FourthPresenterTest(FourthContract.View mView , FourthContract.Model model){
        this.mView = mView ;
        this.model = model ;
        System.out.println("我是FourthPresenter空的Null");
    }

    public FourthPresenterTest(String error){
        System.out.println("我是FourthPresenter不为空的" + error);
    }




    @Override
    public void loginNet() {

        model.loginNet(mView.getUserName() , mView.getPassWord() , null);

    }
}
