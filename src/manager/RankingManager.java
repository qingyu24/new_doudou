package manager;

import core.DBMgr;
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
        String sql = "select `b`.`school` AS `school`,`b`.`grade` AS `grade`,`b`.`banji` AS `banji`,avg(`a`.`Garde`) AS `av_grade`,`c`.`SchoolName` AS `schoolname`,`sd`.`RoleID` AS `RoleID`,`sd`.`xm` AS `tname`,`ac`.`portrait` AS `por`,`c`.`Province` AS `shi`,`sd`.`grade` AS `score` from ((((`zz_huiyuan` `b` left join `account` `a` on((`a`.`RoleID` = `b`.`RoleID`))) left join `zz_school` `c` on((`b`.`school` = `c`.`id`))) left join `zz_huiyuan` `sd` on(((`sd`.`usertype` = 1) and (`b`.`school` = `sd`.`school`) and (`b`.`grade` = `sd`.`grade`) and (`b`.`banji` = `sd`.`banji`)))) left join `account` `ac` on((`sd`.`RoleID` = `ac`.`RoleID`)))  ";

        String inSchool = " Where b.school = " + p_user.getSchool();
        String get = p_user.getCenterData().getM_huiyuan().sheng2.Get();
        String inshi = "  Where c.shi =" + get;
        String group = " group by `b`.`school`,`b`.`grade`,`b`.`banji` order by `a`.`Garde` desc ";
        switch (type) {
            case 1:

                return DBMgr.ReadSQL(new UserClass(), sql + group);
            case 2:
             /*   LogRecord.Log(inshi);*/
                if (get == null || get.equals("")) return null;
                return DBMgr.ReadSQL(new UserClass(), sql + inshi + group);
            case 3:
                return DBMgr.ReadSQL(new UserClass(), sql + inSchool + group);
        }

        return null;
    }

    public ArrayList<UserClass> getRankingByClass(int type, MyUser p_user) {
        ArrayList<UserClass> list = new ArrayList<>();
        UserClass[] userClasses = this.geByClass(type, p_user);

        if (list != null && userClasses != null) {
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


