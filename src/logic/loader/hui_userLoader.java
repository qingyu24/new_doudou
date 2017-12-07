package logic.loader;

import core.DBMgr;
import core.detail.impl.socket.SendMsgBuffer;
import logic.MyUser;
import logic.PackBuffer;
import logic.Reg;
import logic.userdata.CenterDateInterface;
import logic.userdata.hui_user;

import java.util.ArrayList;

public class hui_userLoader /*extends DBLoaderEx<hui_user>*/ {

public  static hui_userLoader instance;

    public static hui_userLoader getInstance() {
        if(instance==null)
            instance=new hui_userLoader();
        return instance;
    }

    public void sendRaning_All(int list_type, MyUser p_user) {
        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_RANKING_PERSON);
        ArrayList<hui_user> list = new ArrayList<hui_user>();
        String sql=null;
        switch (list_type){
            case 1:sql=null;break;
            case 2:sql="province ="+p_user.getCenterData().province;break;
            case 3:sql=" school = "+p_user.getSchool();
        }
        hui_user[] hui_users = DBMgr.ReadSQL(new hui_user(), hui_user.Sql(sql));
        int size = 0;//一共发送前几名
        int isbegin = 1;
        for (hui_user hui_user : hui_users) {

            hui_user next = hui_user;
            if (true) {
                list.add(next);
                if (size++ > 30) break;//一共发送多少人
                if (list.size() == 30) {

                    this.rankBuffer(list, CenterDateInterface.MID_RANKING_PERSON, isbegin, p_user, list_type);
                    list.clear();
                    isbegin = 0;
                }
            }
        }

        if (list.size() > 0)
            this.rankBuffer(list, CenterDateInterface.MID_RANKING_PERSON, isbegin, p_user, list_type);
    }

    private boolean match(hui_user next, int list_type, MyUser p_user) {

        switch (list_type) {
            case 1:
                return true;
            case 2:
                if (next.schoolID.Get() == p_user.getSchool()) return true;
                break;
            case 3:
                if (next.schoolID.Get() == p_user.getSchool() && next.banji.Get() == p_user.getNianJi().get(2))
                    return true;

        }
        return false;
    }

    public void rankBuffer(ArrayList<hui_user> list, int msg, int isbegin, MyUser user, int type) {
        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, msg);
/*        buffer.Add(isbegin);//是否*/
        buffer.Add(type);
        buffer.Add((short) (list.size()));
        for (hui_user hui_user : list) {
            hui_user.packDate(buffer);
        }
        buffer.Send(user);

    }

}
