package netework;

import java.util.Map;

import io.reactivex.Observable;
import utils.NetworkApi;


/**
 * Created by shi on 2017/4/26.
 */

public class UpdateFractory extends UseCase{

    public Map<String, String> map;
    private NetworkApi appService;

    private String name ;

    private static volatile UpdateFractory updateFractory ;

    private UpdateFractory(String name , Map<String,String> map){
        super();
        this.name = name ;
        this.map = map;
        appService = RetrofitServiceFactory.getAppService();

    }

    private static UpdateFractory getUpdateFractory(String name , Map<String,String> map){

        if (updateFractory == null){

            updateFractory = new UpdateFractory(name , map);

        }else{

            updateFractory.setMap(name , map);

        }

        return updateFractory ;

    }

    private void setMap(String name , Map<String,String> map) {

        this.name = name ;
        this.map = map ;
    }


    @Override
    public Observable buildUseCaseObservable() {

        switch (this.name){
            case "updateNetCall":

                return appService.updateNetCall(map) ;


            case "tokenCall":

                return appService.tokenCall() ;


            case "needTokenCall":

                return appService.needTokenCall(map);


        }


        return null ;
    }


    public static Build getBuild(){

        return Build.getBuild();
    }


    public static class Build{


        private Build(){}

        private static final Build single = new Build();

        public static Build getBuild(){

            return single ;

        }

        private Map<String, String> map;
        private String name ;

        public Build map (Map<String, String> map) {
            this.map = map ;
            return this;
        }


        public Build name(String name) {
            this.name = name;
            return this ;
        }

        public UseCase build(){

            return  UpdateFractory.getUpdateFractory(this.name ,this.map);
        }



    }



}
