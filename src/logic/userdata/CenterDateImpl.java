package logic.userdata;

import core.DBMgr;
import core.detail.impl.socket.SendMsgBuffer;
import core.remote.*;
import logic.MyUser;
import logic.PackBuffer;
import logic.Reg;
import logic.loader.hui_userLoader;
import manager.RankingManager;
import manager.UserManager;

import java.util.ArrayList;


public class CenterDateImpl implements CenterDateInterface {

    @Override
    @RFC(ID = 1)
    public void getClass(@PU MyUser p_user) {
        long l = System.currentTimeMillis();

        UserManager.getInstance().getClassmates(p_user);

        System.out.println(System.currentTimeMillis() - l);
        ;
    }

    @Override
    @RFC(ID = 2)
    public void addFriend(@PU MyUser p_user, @PL long friendID) {
        // TODO Auto-generated method stub
        MyUser user = UserManager.getInstance().getUser(friendID);
        if (user != null && user.hasFriend(p_user.GetRoleGID()) == 0) {
            SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_ADDFRIEND);
            p_user.packDate(buffer);
            buffer.Send(user);
        } else {
            //如果玩家不在线或者已经是好友返回错误信息

        }

    }

    @Override
    @RFC(ID = 3)
    public void agreeAdd(@PU MyUser p_user, @PL long f_ID) {
        // TODO Auto-generated method stub
        MyUser user = UserManager.getInstance().getUser(f_ID);

        if (user != null) {
            p_user.addFriend(user);
            user.addFriend(p_user);
            SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_AGRREADD);
            p_user.packDate(buffer);
            buffer.Send(user);
        }
        UserManager.getInstance().addFriends(p_user.GetRoleGID(), f_ID);
    }

    @Override
    @RFC(ID = 5)
    public void givePresent(@PU MyUser p_user, @PL long friendID, @PI int giftID, @PI int price, @PS String name) {
        // TODO Auto-generated method stub
        if (p_user.GetMoney() >= price) {
            boolean b = p_user.buyShopping(friendID, giftID, price);
            MyUser user = UserManager.getInstance().getUser(friendID);
            if (user != null&&b) {
                SendMsgBuffer p = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_SHOP_Received);
                p_user.packDate(p);
                p.Add(giftID);
                p.Add(price);
                p.Add(name);
/*                user.packBaseData(p);*/
                p.Send(user);
            }

                SendMsgBuffer p = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_SHOP_PRESENT);
              p.Add(price);
            p.Add(b?1:0);
                p.Send(p_user);

        } else {

            //金币不足购买
            SendMsgBuffer p = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_SHOP_PRESENT);
            p.Add(0);
            p.Send(p_user);

        }
    }

    @Override
    @RFC(ID = 6)
    public void buyShopping(@PU MyUser p_user, @PI int giftID, @PI int price) {
        // TODO Auto-generated method stub
        if (p_user.GetMoney() >= price) {
            boolean b = p_user.buyShopping(p_user.GetRoleGID(), giftID, price);
            if (b) {
                p_user.sendSucess(CenterDateInterface.MID_SHOP_BUY, 1);
                return;
            }
        }
        //金币不足购买或购买失败
        p_user.sendSucess(CenterDateInterface.MID_SHOP_BUY, 0);


    }

    @Override
    @RFC(ID = 7)
    public void userHome(@PU MyUser p_user, @PL long targerID) {
        // TODO Auto-generated method stub
        if (targerID == 0) {
            SendMsgBuffer p = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_USER_HOME);
            boolean b = p_user.packBaseData(p);
            p.Send(p_user);
        } else {
            MyUser user = UserManager.getInstance().getUser(targerID);
            if (user != null) {
                SendMsgBuffer p = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_USER_HOME);
                boolean b = user.packBaseData(p);
                p.Send(p_user);
            }
        }

    }

    @Override
    @RFC(ID = 8)
    public void sendMessage(@PU MyUser p_user, @PL long targetID, @PS String message) {
        MyUser user = UserManager.getInstance().getUser(targetID);
        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_USER_MESSAGE);
        p_user.packDate(buffer);
        buffer.Add(message);
        buffer.Send(user);


    }

    /**
     * @param p_user
     * @param targetID
     */
    @Override
    @RFC(ID = 4)
    public void deleteFriends(@PU MyUser p_user, @PL long targetID) {
        boolean b = p_user.deleteFriend(targetID);
        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_USER_DELETE);

        buffer.Add(b);
        buffer.Send(p_user);

    }

    @Override
    @RFC(ID = 9)
    public void rankingClass(@PU MyUser p_user, @PI int list_type, @PI int number, @PI int size) {
/*        UserClass[] list = new UserClass[0];*/
        ArrayList<UserClass> list = new ArrayList<>();
        switch (list_type) {
            case 1://班级所有

            case 2://班级在市

            case 3: //班级在学校

        }

        list = RankingManager.getInstance().getRankingByClass(list_type, p_user);
        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_RANKING_CLASS);

        buffer.Add(list_type);

       /* buffer.Add((short) (list.length>30?30:list.length));*/
       buffer.Add((short)list.size());
        int i=0;
        for (UserClass userClass : list) {
            if(i++>30) break;
            userClass.packdate(buffer);
            System.out.println("班级分数"+userClass.av_grade.Get());
        }
        buffer.Send(p_user);

    }

    @Override
    @RFC(ID = 10)
    public void changeSkin(@PU MyUser p_user, @PI int type, @PI int number) {
        //type   1 头像 2皮肤
    /*    if (number==0){
        p_user.getskin(type);}*/
        if (type == 2 && number == 0) {
            ArrayList<Integer> list = p_user.getskinlist();
            SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_USER_SKIN);

            buffer.Add((short) list.size());
            for (Integer integer : list) {
                buffer.Add(integer);
            }
            buffer.Send(p_user);
            return;
        }

        boolean b = false;
        if (type == 1) {
            p_user.getCenterData().getM_account().portrait.Set(number);
            b = DBMgr.ExecuteSQL("UPDATE account SET  portrait =" + number + " Where roleid =" + p_user.GetRoleGID());
        }

        if (type == 2) {
            p_user.getCenterData().getM_account().Skin.Set(number);
            b = DBMgr.ExecuteSQL("UPDATE account SET  Skin =" + number + " Where roleid =" + p_user.GetRoleGID());

        }


    }

    @Override
    @RFC(ID = 11)
    public void searchUser(@PU MyUser p_user, @PS String UserName) {
        ArrayList<MyUser> users = UserManager.getInstance().searchByName(UserName);
        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_USER_SEARCH);
        buffer.Add((short) users.size());
        for (MyUser user : users) {
            user.packDate(buffer);
            buffer.Add(UserManager.getInstance().hasFriend(p_user.GetRoleGID(),user.GetRoleGID())?1:0);
            buffer.Add(user.isTeacher());

        }
        buffer.Send(p_user);
    }

    @Override
    @RFC(ID = 12)
    public void rankingPerson(@PU MyUser p_user, @PI int list_type, @PI int number, @PI int size) {

        //1 all   2市  3 school
        hui_userLoader loader = hui_userLoader.getInstance();
        loader.sendRaning_All(list_type, p_user);

    }


}
