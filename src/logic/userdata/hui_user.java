package logic.userdata;

import core.db.DBInt;
import core.db.DBLong;
import core.db.DBString;
import core.db.RoleDataBase;
import core.detail.impl.socket.SendMsgBuffer;
import logic.LogRecord;
import logic.MyUser;
import manager.UserManager;

public class hui_user extends RoleDataBase {
    public DBLong RoleID;
    public DBInt schoolID;
    public DBString school;
    public DBInt grade;
    public DBInt banji;
    public DBInt score;
    public DBInt portrait;
    public DBString xm;
    public DBInt usertype;
    public DBString type;

    public void packDate(SendMsgBuffer buffer, MyUser p_user) {
        buffer.Add(this.RoleID.Get());  //id
        buffer.Add(this.xm.Get());//����
        buffer.Add(this.portrait.Get() == 0 ? 1 : this.portrait.Get());//ͷ��ID
        buffer.Add(CountGrade.getInstance().getnewLevel(this.score.Get()));
        buffer.Add(CountGrade.getInstance().getnewStar(this.score.Get()));
        buffer.Add(this.grade.Get());
        buffer.Add(this.banji.Get());
        buffer.Add(UserManager.getInstance().hasFriend(p_user.GetRoleGID(), this.RoleID.Get()) ? 1 : 0);
        buffer.Add(this.usertype.Get() == 1 ? 1 : 0);

    }

    public void packDate(SendMsgBuffer buffer) {
        buffer.Add(RoleID.Get());
        buffer.Add(this.xm.Get());//����
        buffer.Add(this.portrait.Get() == 0 ? 1 : this.portrait.Get());//ͷ��ID
        buffer.Add(CountGrade.getInstance().getnewLevel(this.score.Get()));
        buffer.Add(CountGrade.getInstance().getnewStar(this.score.Get()));
/*        buffer.Add(school.Get());*/
/*        buffer.Add(this.grade.Get());
        buffer.Add(this.banji.Get());*/

    }

    public static String Sql(String where) {
        StringBuilder sql = new StringBuilder("select top 50 zz_huiyuan.RoleID AS RoleID,zz_huiyuan.school AS schoolID,zz_school.SchoolName AS school,zz_huiyuan.grade AS grade,zz_huiyuan.banji AS banji,account.Garde AS score,account.portrait AS portrait,zz_huiyuan.xm AS xm,zz_huiyuan.usertype AS usertype,zz_usertype.title AS type from (((zz_huiyuan left join account on((account.RoleID = zz_huiyuan.RoleID))) left join zz_school on((zz_huiyuan.school = zz_school.id))) left join zz_usertype on((zz_usertype.ID = zz_huiyuan.usertype))) ");
        if (where != null) {
            sql.append(" where  ");
            sql.append(where);
        }
        sql.append("  order by account.Garde desc");

        return sql.toString();
    }
}
