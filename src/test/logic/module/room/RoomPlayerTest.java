package test.logic.module.room;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * RoomPlayer Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十一月 29, 2017</pre>
 */
public class RoomPlayerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getGrade()
     */
    @Test
    public void testGetGrade() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setVisit(long visit)
     */
    @Test
    public void testSetVisit() throws Exception {
//TODO: Test goes here...
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://218.25.89.91:1433;DatabaseName=pp22peixun";
        String userName = "sa";
        String userPwd = "ppsql_~!@*123-cndata++";

        Class.forName(driverName);
        Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
        Statement statement = dbConn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)FROM zz_huiyuan ");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
        }
        System.out.println("连接数据库成功");


    }

    /**
     * Method: getVisitID()
     */
    @Test
    public void testGetVisitID() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRanking()
     */
    @Test
    public void testGetRanking() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setRanking(int ranking)
     */
    @Test
    public void testSetRanking() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: init(MyUser user)
     */
    @Test
    public void testInit() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getVisit(MyUser user)
     */
    @Test
    public void testGetVisit() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getRoleId()
     */
    @Test
    public void testGetRoleId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: packData(SendMsgBuffer buffer)
     */
    @Test
    public void testPackData() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: packDataInit(SendMsgBuffer buffer)
     */
    @Test
    public void testPackDataInit() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSkin()
     */
    @Test
    public void testGetSkin() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setSkin(int skin)
     */
    @Test
    public void testSetSkin() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTeamName()
     */
    @Test
    public void testGetTeamName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setTeamName(int teamName)
     */
    @Test
    public void testSetTeamName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTeamID()
     */
    @Test
    public void testGetTeamID() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setTeamID(int teamID)
     */
    @Test
    public void testSetTeamID() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: splitBody()
     */
    @Test
    public void testSplitBody() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getID()
     */
    @Test
    public void testGetID() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setID(int id)
     */
    @Test
    public void testSetID() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUser()
     */
    @Test
    public void testGetUser() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: changeSpeed(int xp, int yp)
     */
    @Test
    public void testChangeSpeed() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateBody()
     */
    @Test
    public void testUpdateBody() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updatePlace(ArrayList<Integer> list)
     */
    @Test
    public void testUpdatePlace() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: killone()
     */
    @Test
    public void testKillone() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: near(RoomPlayer ru)
     */
    @Test
    public void testNear() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addQiu()
     */
    @Test
    public void testAddQiu() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPlaybodylist()
     */
    @Test
    public void testGetPlaybodylist() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: setPlaybodylist(ArrayList<PlayerBody> playbodylist)
     */
    @Test
    public void testSetPlaybodylist() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getPlaybody(int bodyId)
     */
    @Test
    public void testGetPlaybody() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: compareTo(RoomPlayer o)
     */
    @Test
    public void testCompareTo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getWeight()
     */
    @Test
    public void testGetWeight() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addSplitQiu(RoomQiu qiu)
     */
    @Test
    public void testAddSplitQiu() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: reset()
     */
    @Test
    public void testReset() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: packScore(SendMsgBuffer buffer)
     */
    @Test
    public void testPackScore() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: endpack(SendMsgBuffer buffer, int allPlayer)
     */
    @Test
    public void testEndpack() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: calGame(int size)
     */
    @Test
    public void testCalGame() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: destroy()
     */
    @Test
    public void testDestroy() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: getMoney()
     */
    @Test
    public void testGetMoney() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = RoomPlayer.getClass().getMethod("getMoney"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: EarnMoney(int ranking)
     */
    @Test
    public void testEarnMoney() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = RoomPlayer.getClass().getMethod("EarnMoney", int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
