package module

import contract.SecondContract
import dagger.Module
import dagger.Provides
import model.SecondModel

/**
 * description:创建view层,跟modeld的注解,直接在presenter中根据注解得到实例对象
 * Creat by shiqiang on 2018/5/3 0003 10:34
 */
@Module
class SecondActivityModule(private val mView: SecondContract.View) {


    @Provides
    fun provideActivity(): SecondContract.View {
        return mView
    }

    @Provides
    fun provideSecondModel(): SecondContract.Model {
        return SecondModel()
    }
}
