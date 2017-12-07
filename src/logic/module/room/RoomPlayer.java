package logic.module.room;

import core.detail.impl.socket.SendMsgBuffer;
import logic.LogRecords;
import logic.MyUser;
import logic.eGameType;
import logic.loader.SchoolLoader;
import logic.userdata.CountGrade;
import logic.userdata.Team;
import manager.LoaderManager;
import manager.RoomManager;
import manager.TeamManager;
import utility.Rand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class RoomPlayer implements Comparable<RoomPlayer> {

    private ArrayList<PlayerBody> playbodylist;
    private HashMap<Integer, PlayerBody> playbodyMap;
    private MyUser m_user;
    private int m_id;// 玩家ID
    private int bodyNum;// 身体个数计数
    private int sunQiu;// 吐球个数计数
    private ArrayList<RoomQiu> m_Qius;// 玩家球
    private int maxQiu;// 球最大的ID
    private int ranking;// 名次
    private int eatNum;// 吞噬次数
    private int coin;// 本局获得金币数目
    private int teamID;
    private int Skin; // 皮肤
    private long m_timeid;

    private boolean isVisit;
    private long visitID;
/*	private int teamName;*/
    /* private CountGrade grade; */

    public CountGrade getGrade() {
        // TODO Auto-generated method stub
        return this.m_user.getGrade();
    }

    public void setVisit(long visit) {
        isVisit = true;
        visitID=visit;
    }

    public long getVisitID() {
        return visitID;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void init(MyUser user) {
        m_user = user;
        bodyNum = 1;
        int x = Rand.Get(100000);
        int y = Rand.Get(100000);
        PlayerBody pb = new PlayerBody(1, x, y);
        bodyNum++;
        this.playbodylist = new ArrayList<PlayerBody>();
        this.playbodyMap = new HashMap<Integer, PlayerBody>();
        this.playbodylist.add(pb);
        this.playbodyMap.put(pb.getM_id(), pb);
        sunQiu = 0;
        maxQiu = 0;
        eatNum = 0;
        coin = 0;
        m_Qius = new ArrayList<RoomQiu>();
        user.getGrade().enterRoom();
        teamID = 0;
        Skin = 0;
        isVisit = false;

        /*teamName = 0;*/
    }

    public void getVisit(MyUser user) {
        m_user = user;
        bodyNum = 0;

    }

    public long getRoleId() {
        return m_user.GetRoleGID();

    }

    public void packData(SendMsgBuffer buffer) {

        buffer.Add(m_id);
        buffer.Add((short) this.playbodylist.size());
        Iterator<PlayerBody> it = this.playbodylist.iterator();
        while (it.hasNext()) {
            PlayerBody pb = it.next();
            pb.packData(buffer);
        }
        buffer.Add(maxQiu);
        /* buffer.Add(teamID); */

    }

    public void packDataInit(SendMsgBuffer buffer) {
		/* buffer.Add(this.getRoleId()); */
        buffer.Add(m_id);
        buffer.Add((short) this.playbodylist.size());
        Iterator<PlayerBody> it = this.playbodylist.iterator();

        while (it.hasNext()) {
            PlayerBody pb = it.next();

            pb.packData(buffer);
        }
        buffer.Add(maxQiu);
        buffer.Add(this.m_user.getTickName());

        buffer.Add(this.getSkin());// 皮肤编号

        buffer.Add(teamID);// 队伍信息
        buffer.Add(this.getUser().getCenterData().getNianJi());

        System.out.println("sssssssss当前队伍ＩＤ" + this.getSkin());

		/*
		 * buffer.Add(1);//头像编号
		 */
    }

    public int getSkin() {
        Team team = TeamManager.getInstance().getTeam(teamID);
        if (team != null) {
            return team.getTeamName();
        }
        this.Skin = m_user.getSkin();
        return Skin;
    }

    public void setSkin(int skin) {
        Skin = skin;
    }

    public int getTeamName() {
        Team team = TeamManager.getInstance().getTeam(teamID);
        if (team != null) {
            return team.getTeamName();
        }
        return 0;
    }

    public void setTeamName(int teamName) {
        this.setSkin(teamName);
		/*this.teamName = teamName;*/
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public void splitBody() {
		/*
		 * PlayerBody pdo = playbodylist.get(0); PlayerBody pb = new
		 * PlayerBody(playbodylist.size(), pdo.getM_xpos(), pdo.getM_ypos());
		 * this.playbodylist.add(pb); this.playbodyMap.put(pb.getM_id(),pb);
		 */

    }

    public int getID() {
        return m_id;
    }

    public void setID(int id) {
        m_id = id;
    }

    public MyUser getUser() {
        return m_user;
    }

    public void changeSpeed(int xp, int yp) {
        Iterator<PlayerBody> it = this.playbodylist.iterator();
        while (it.hasNext()) {
            PlayerBody pb = it.next();
            pb.changeSpeed(xp, yp);
        }
    }

    public void updateBody() {
        Iterator<PlayerBody> it = this.playbodylist.iterator();
        while (it.hasNext()) {
            PlayerBody pb = it.next();
            pb.updatePosition();
        }
    }

    public void updatePlace(ArrayList<Integer> list) {
        // TODO Auto-generated method stub
        int num = list.size() / 7;
        while (playbodylist.size() < num && playbodylist.size() != 0) {
            PlayerBody pdo = playbodylist.get(0);
            PlayerBody pb = new PlayerBody(bodyNum, pdo.getM_xpos(), pdo.getM_ypos());
            bodyNum++;
            this.playbodylist.add(pb);
            this.playbodyMap.put(pb.getM_id(), pb);
        }

        while (playbodylist.size() > num && playbodylist.size() != 0) {
            PlayerBody remove = this.playbodylist.remove(0);
            this.playbodyMap.remove(remove.getM_id());
        }

        for (int i = 0; i * 7 < list.size(); i++) {
            // 避免数组越界
            if (playbodylist.size() > 0) {
                if (list.size() >= i * 7 + 7) {
      /*          PlayerBody pb = playbodyMap.get(list.get(i * 7 + 0));*/
                    PlayerBody pb = playbodylist.get(i);
                    if (pb != null) {
                        pb.updatePosition(list.get(i * 7), list.get(i * 7 + 1), list.get(i * 7 + 2), list.get(i * 7 + 3),
                                list.get(i * 7 + 4), list.get(i * 7 + 5), list.get(i * 7 + 6));

                    }
                }
            }
        }

    }

    public void killone() {
        // TODO Auto-generated method stub
        this.eatNum += 1;
    }

    // 判断玩家之间距离 需不需要发送消息
    public boolean near(RoomPlayer ru) {
        // TODO Auto-generated method stub
        if (playbodylist.size() > 0) {
            PlayerBody b1 = this.playbodylist.get(0);
            ArrayList<PlayerBody> list = ru.getPlaybodylist();
            if (list.size() > 0) {
                PlayerBody b2 = list.get(0);
                if (b1.near(b2)) {
                    // 如果在附近就发送当前玩家位置信息
                    return true;
                }
            }
        }
        return true;
    }

    public void addQiu() {
        // TODO Auto-generated method stub

    }

    public ArrayList<PlayerBody> getPlaybodylist() {
        return playbodylist;
    }

    public void setPlaybodylist(ArrayList<PlayerBody> playbodylist) {
        this.playbodylist = playbodylist;
    }

    public PlayerBody getPlaybody(int bodyId) {
        Iterator<PlayerBody> it = playbodylist.iterator();
        while (it.hasNext()) {
            PlayerBody playerBody = (PlayerBody) it.next();
            if (playerBody.getM_id() == bodyId) {

                return playerBody;
            }
        }
        return null;
    }

    @Override
    public int compareTo(RoomPlayer o) {
        // TODO Auto-generated method stub
        if (o.getWeight() > this.getWeight()) {
            return 1;
        } else if (o.getWeight() < this.getWeight()) {
            return -1;
        }
        return 0;
    }

    public int getWeight() {
        // TODO Auto-generated method stub
        int i = 0;
        Iterator<PlayerBody> it = getPlaybodylist().iterator();
        while (it.hasNext()) {
            PlayerBody playerBody = (PlayerBody) it.next();
            i += playerBody.getM_weight();
        }
        if (isVisit) {
            return -1;
        }
        return i;
    }

    // 吐出来的球的信息
    public void addSplitQiu(RoomQiu qiu) {
        // TODO Auto-generated method stub
        this.m_Qius.add(qiu);
        maxQiu++;
    }

    //
    // 复活重置
    public PlayerBody reset() {
        // TODO Auto-generated method stub

        bodyNum = 1;
        int x = Rand.Get(100000);
        int y = Rand.Get(100000);
        PlayerBody pb = new PlayerBody(1, x, y);
        bodyNum++;
        playbodylist.clear();
        playbodyMap.clear();
        this.playbodylist.add(pb);
        this.playbodyMap.put(pb.getM_id(), pb);
        sunQiu = 0;
        maxQiu = 0;
        m_Qius = new ArrayList<RoomQiu>();
        return pb;
    }

    public void packScore(SendMsgBuffer buffer) {
        // TODO Auto-generated method stub
        buffer.Add(m_id);
        buffer.Add(ranking);

    }

    public void endpack(SendMsgBuffer buffer,int allPlayer) {
        // TODO Auto-generated method stub

        buffer.Add(this.getID());
        // 头像0
        buffer.Add(this.m_user.getPortrait());
        // 用户名
        buffer.Add(this.m_user.getTickName());
        // 学校 int


        buffer.Add(SchoolLoader.getInstance().getSchool(this.m_user.getSchool()));//
        buffer.Add(eatNum);
        buffer.Add(this.getWeight());
        LogRecords.Log(null, "现在体重" + (this.getWeight() == 0 ? 10 : this.getWeight()));
     if(allPlayer>=15) {
         buffer.Add(this.EarnMoney(this.getRanking()));// 获得金币
     }else{
         buffer.Add(0);
     }
        buffer.Add(this.getTeamName());
        buffer.Add(this.getRanking());
    }

    private int getMoney() {

        return this.m_user.getCenterData().getMoney();
    }

    // 玩家结算
    public void calGame(int size) {
        // TODO Auto-generated method stub
        if (this.getGrade().storeChange(this.ranking)) {


            this.m_user.changeScore(1);
        }
        this.m_user.changeMoney(EarnMoney(ranking));
/*
        this.m_user.changeMoney(EarnMoney(ranking));*/
    }

    private int EarnMoney(int ranking) {
        Room room = RoomManager.getInstance().getRoom(this.getRoleId());
        if (room != null) {
            RoomRule rr = room.getRr();

            if (rr.getM_type() == eGameType.SOLO) {
                if (ranking == 1) return 25;
                else if (ranking == 2) return 20;
                else if (ranking == 3) return 15;
                else if (ranking == 4) return 10;
                else if (ranking == 5) return 8;
                else if (ranking == 6) return 5;
                else if (ranking == 7) return 4;
                else if (ranking == 8) return 3;
                else if (ranking == 9) return 2;
                else if (ranking == 10) return 1;
                else if (ranking > 10) return 0;
            } else if (rr.getM_type() == eGameType.TEAM) {
                if (rr.isFree()) {//团战系统房
                    if (ranking == 1) return 10;
                    else if (ranking == 2) return 5;
                    else if (ranking == 3) return 2;
                    else return 0;
                } else {//团战自建房
                    if (rr.getTeamNum() == 3) {
                        if (ranking == 1) return 10;
                        if (ranking == 2) return 5;
                        else return 0;

                    } else if (rr.getTeamNum() == 5 || rr.getTeamNum() == 6) {
                        if (ranking == 1) return 10;
                        else if (ranking == 2) return 5;
                        else if (ranking == 3) return 2;
                        else return 0;
                    }
                }
            }

        }

        return 0;

    }


    public void destroy() {
    /*    Root.GetInstance().RemoveTimer(this.m_timeid);*/
        for (PlayerBody body : playbodylist) {
            body.destroy();
        }
    }
}
