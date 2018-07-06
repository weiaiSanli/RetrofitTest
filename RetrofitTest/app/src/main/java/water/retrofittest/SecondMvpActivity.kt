package water.retrofittest

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

import javax.inject.Inject

import components.AppComponent
import components.DaggerSecondActivityComponent
import contract.SecondContract
import module.ActivityModule
import module.SecondActivityModule
import presenter.SecondPresenter
import org.jetbrains.anko.find

/**注意: Component会首先从Module维度中查找类实例，若找到就用Module维度创建类实例，并停止查找Inject维度。
 * 否则才是从Inject维度查找类实例。所以创建类实例级别Module维度要高于Inject维度。
 *
 * description:第二种引入的方式,在module中没有SecondPresenter实例的引用,就会在Inject里面找寻
 * Creat by shiqiang on 2018/5/3 0003 10:30
 */

class SecondMvpActivity : BaseActivity(), SecondContract.View, View.OnClickListener {


    @Inject
    lateinit var presenter: SecondPresenter
    private val TAG = "SecondMvpActivity"
    private lateinit var tv: TextView
    private lateinit var bt: Button


    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

        tv = find(R.id.tv_end)
        bt = find(R.id.bt_login)
        bt.setOnClickListener(this)
    }

    override fun initData() {

    }

    /**
     * 第二种方式导入presenter
     * 重写基类的setupActivityComponent()方法
     */
    override fun setupActivityComponent(appComponent: AppComponent) {

        DaggerSecondActivityComponent.builder()
                .appComponent(appComponent)
                .secondActivityModule(SecondActivityModule(this))
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }


    override fun getUserName(): String {
        return "xingfushuizhan"
    }

    override fun getPassWord(): String {
        return "123456"
    }

    override fun loginSuccess(success: String) {

        Log.e("TAG", success)
        tv.text = success
    }

    override fun error(error: String) {
        Log.e("TAG", error)
        tv.text = error
    }


    override fun onClick(v: View) {

        when(v.id){
            R.id.bt_login -> presenter.loginNet()
            else ->{
                print("this is null")
            }
        }

    }
}
