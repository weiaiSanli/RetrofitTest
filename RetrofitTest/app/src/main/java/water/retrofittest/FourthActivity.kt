package water.retrofittest

import android.view.View
import android.widget.Button
import android.widget.TextView
import components.AppComponent
import components.DaggerFourthActivityComponent
import contract.FourthContract
import module.ActivityModule
import module.FourthActivityModule
import module.Named
import presenter.FourthPresenter
import utils.Constant
import javax.inject.Inject
import org.jetbrains.anko.find

/**
 * created by shi on 2018/7/6/006
 *
 */
class FourthActivity : BaseActivity() , FourthContract.View, View.OnClickListener {


    /**
     * lateinit:1. lateinit 延迟加载 2.lateinit 只能修饰, 非kotlin基本类型
     * 因为Kotlin会使用null来对每一个用lateinit修饰的属性做初始化，而基础类型是没有null类型，所以无法使用lateinit。
     */
    private lateinit var tv : TextView
    private lateinit var bt : Button

    @Inject
    @Named("presenter")
    lateinit var presenter: FourthPresenter

    override fun getUserName(): String? {
        return Constant.userName //调用静态属性
    }

    override fun getPassWord(): String {
        return Constant.passWord
    }

    override fun loginSuccess(success: String) {

        tv.text = success

    }

    override fun error(error: String) {
        tv.text = error
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        tv =  find (R.id.tv_end)
        bt = find (R.id.bt_login)
        bt.setOnClickListener(this)

    }

    override fun initData() {

    }

    override fun setupActivityComponent(appComponent: AppComponent?) {

        DaggerFourthActivityComponent.builder()
                .appComponent(appComponent)
                .fourthActivityModule(FourthActivityModule(this))
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)



    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.bt_login -> presenter.loginNet()

            else ->{
                print("this view is null")
            }

        }
    }
}