package manager;

import core.DBMgr;
import logic.LogRecord;
import logic.MyUser;
import logic.userdata.UserClass;

import java.util.ArrayList;
import java.util.Collections;


public class RankingManager {
    private static RankingManager instance;


    public static RankingManager getInstance() {

        if (instance == null) {
            instance = new RankingManager();
        }
        return instance;

    }

    public UserClass[] geByClass(int type, MyUser p_user) {

        String all = "SELECT * FROM class";
        String inSchool = "select * from class Where school = " + p_user.getSchool();
        String get = p_user.getCenterData().getM_huiyuan().sheng2.Get();
        String inshi = "select * from class Where shi =" + get;

        switch (type) {
            case 1:
                return DBMgr.ReadSQL(new UserClass(), all);
            case 2:
             /*   LogRecord.Log(inshi);*/
                if(get==null||get.equals(""))return null;
             return  DBMgr.ReadSQL(new UserClass(),inshi);
            case 3:
                return DBMgr.ReadSQL(new UserClass(), inSchool);
        }

        return null;
    }
    public ArrayList<UserClass> getRankingByClass(int type, MyUser p_user) {
        ArrayList<UserClass> list = new ArrayList<>();
        UserClass[] userClasses = this.geByClass(type, p_user);

    if(list!=null&&userClasses!=null) {
        for (UserClass userClass : userClasses) {
            if (userClass.av_grade.Get() != 0) {
                list.add(userClass);
            }
        }
    }
        Collections.sort(list);
        return list;
    }


}


