package manager;

import core.DBMgr;
import core.detail.impl.socket.SendMsgBuffer;
import logic.MyUser;
import logic.PackBuffer;
import logic.Reg;
import logic.eGameState;
import logic.loader.HuiyuanLoader;
import logic.module.room.Room;
import logic.module.room.RoomPlayer;
import logic.userdata.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UserManager {

    private static UserManager _instance;
    private static String addFriends = "insert into friends (RoleID,FriendID) values (%d,%d) ";
    private ArrayList<MyUser> users = new ArrayList<MyUser>();
    private HashMap<Long, MyUser> m_users = new HashMap<Long, MyUser>();

    public static UserManager getInstance() {
        if (_instance != null) {
            return _instance;
        }
        return _instance = new UserManager();
    }

    public void addUser(MyUser user) {
        // TODO Auto-generated method stub
        users.add(user);
        m_users.put(user.GetRoleGID(), user);
        ArrayList<MyUser> friends = user.getFriends();
        if (friends != null) {
            Iterator<MyUser> it = friends.iterator();
            while (it.hasNext()) {
                MyUser myUser2 = (MyUser) it.next();
                myUser2.friendsOnline(user);
            }
        }
    }

    public MyUser getUser(Long id) {
        // TODO Auto-generated method stub
        return m_users.get(id);
    }

    public void removerUer(MyUser myUser) {
        // TODO Auto-generated method stub
        users.remove(myUser);
        m_users.remove(myUser.GetRoleGID());
        ArrayList<MyUser> friends = new ArrayList<MyUser>();
        if (myUser.getFriends() != null) {
            friends.addAll(myUser.getFriends());

            if (friends != null) {
                Iterator<MyUser> it = friends.iterator();
                while (it.hasNext()) {
                    MyUser myUser2 = (MyUser) it.next();

                    myUser2.friendsOffline(myUser);
                }
            }
        }
    }

    // 测试类 全部加为好友
    public void testFriend() {
        // TODO Auto-generated method stub

        HuiyuanLoader loader = new HuiyuanLoader(new zz_huiyuan());
        try {
            loader.OnLoad();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Iterator<zz_huiyuan> it = loader.getCenterDate().iterator();

        while (it.hasNext()) {
            zz_huiyuan h1 = (zz_huiyuan) it.next();
            Iterator<zz_huiyuan> it2 = loader.getCenterDate().iterator();
            while (it2.hasNext()) {
                zz_huiyuan h2 = (zz_huiyuan) it2.next();

                if (h1.RoleID.Get() != h2.RoleID.Get()) {
                    String s = "insert into friends(roleid,friendid) values(%d,%d)";
                    DBMgr.ExecuteSQL(String.format(s, h1.RoleID.Get(), h2.RoleID.Get()));
                }

            }

        }

    }

    //同伴同學
    public void getClassmates(MyUser p_user) {
        // TODO Auto-generated method stub
        List<Integer> nianJi = p_user.getNianJi();

        ArrayList<hui_user> list = new ArrayList<hui_user>();

     String sql=" zz_huiyuan.school=%d and zz_huiyuan.grade=%d and zz_huiyuan.banji=%d";

        hui_user[] hui_users = DBMgr.ReadSQL(new hui_user(), hui_user.Sql(String.format(sql, nianJi.get(0), nianJi.get(1), nianJi.get(2))));

        int isbegin = 1;
        ArrayList<hui_user> huis = new ArrayList<>();

        for (hui_user user : hui_users) {
            if (user.RoleID.Get() != p_user.GetRoleGID()) {
                huis.add(user);
                if (huis.size() > 30 ) {
                    this.sendClass(huis, p_user, isbegin);
                    huis.clear();
                    isbegin = 0;
                }
            }
        }
    }

    public void sendClass(ArrayList<hui_user> list, MyUser p_user, int isbegin) {

        SendMsgBuffer buffer = PackBuffer.GetInstance().Clear().AddID(Reg.CENTERDATA, CenterDateInterface.MID_CLASS);
        buffer.Add(isbegin);

        buffer.Add((short) (list.size() > 30 ? 30 : list.size()));
        int i = ((short) (list.size() > 30 ? 30 : list.size()));
        for (int i1 = 0; i1 < list.size(); i1++) {

            hui_user hui = list.get(i1);

            buffer.Add(hui.RoleID.Get());  //id
            buffer.Add(hui.xm.Get());//姓名
            buffer.Add(hui.portrait.Get() == 0 ? 1 : hui.portrait.Get());//头像ID
            buffer.Add(CountGrade.getInstance().getnewLevel(hui.score.Get()));
            buffer.Add(CountGrade.getInstance().getnewStar(hui.score.Get()));
            buffer.Add(hui.grade.Get());
            buffer.Add(hui.banji.Get());
            buffer.Add(this.hasFriend(p_user.GetRoleGID(), hui.RoleID.Get()) ? 1 : 0);
            buffer.Add(hui.usertype.Get() == 1 ? 1 : 0);
        }
        buffer.Send(p_user);

    }

    public boolean hasFriend(long l, long get) {
        String sql = "select * from  friends where roleid= %d and friendid=%d";

        Friends[] friends = DBMgr.ReadSQL(new Friends(), String.format(sql, l, get));
        Friends[] friend = DBMgr.ReadSQL(new Friends(), String.format(sql, get, l));

        return friends.length > 0 || friend.length > 0||l==get;
    }

    public void addFriends(long l, long f_id) {
        DBMgr.ExecuteSQL(String.format(addFriends, l, f_id));
        DBMgr.ExecuteSQL(String.format(addFriends, f_id, l));
    }


    public boolean hasUser(String p_username, String p_password) {
        String sql = "select * from zz_huiyuan where username='%s' and password='%s'";
        zz_huiyuan[] zz_huiyuans = DBMgr.ReadSQL(new zz_huiyuan(), String.format(sql, p_username, p_password));
        return zz_huiyuans.length > 0;
    }

    public ArrayList<MyUser> searchByName(String userName) {
        ArrayList<MyUser> myUsers = new ArrayList<>();
        for (MyUser user : this.users) {
            if (user.getTickName() != null && user.getTickName().equals(userName)) {
                myUsers.add(user);
            }
        }
        return myUsers;
    }


    public MyUser getRandomPlayingUser() {
        int i = 0;
        while (i < users.size()) {
            i++;
            MyUser myUser = this.users.get((int) (Math.random() * users.size()));
            Room room = RoomManager.getInstance().getRoom(myUser.GetRoleGID());
            if (room != null && room.getM_state() == eGameState.GAME_PLAYING) {
                RoomPlayer rp = room.GetPlayer(myUser);
                if(rp!=null&&rp.getID()!=0){
                myUser.setType(room.getRr().getM_type());
                return myUser;}
            }
        }
        return null;
    }

    public List<MyUser> getRandomList() {
        List<MyUser> myUsers = new ArrayList<>(4);
        int i=0;
        while (myUsers.size()<4){

            MyUser user = this.getRandomPlayingUser();
        if(user!=null&&!myUsers.contains(user))
        {
            myUsers.add(user);
        }
        if(i++>=40)
            return  myUsers;
        }
        return myUsers;
    }

}
