package test.robot;

public class RFCFn
{
	public static void Login_Enter(Robot r, String p_username, String p_password, int p_nServerID, String p_deviceIdentifier, String p_deviceModel)
	{
		r.GetSendBuffer().Clear().AddID(0,0).Add(p_username).Add(p_password).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Send(r.GetLink());
	}

	public static void Login_Register(Robot r, String p_username, String p_password, int p_nServerID, String p_deviceIdentifier, String p_deviceModel)
	{
		r.GetSendBuffer().Clear().AddID(0,2).Add(p_username).Add(p_password).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Send(r.GetLink());
	}

	public static void Login_ResetEnter(Robot r, String p_username, String p_password, int userType, int p_nServerID, String p_deviceIdentifier, String p_deviceModel, int code)
	{
		r.GetSendBuffer().Clear().AddID(0,3).Add(p_username).Add(p_password).Add(userType).Add(p_nServerID).Add(p_deviceIdentifier).Add(p_deviceModel).Add(code).Send(r.GetLink());
	}

	public static void CharacterImpl_RequestBaseInfo(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(5,0).Send(r.GetLink());
	}

	public static void RoomImpl_EatBody(Robot r, int eatType, int playerId, int bodyId, int xpos, int ypos, int TargetPlayerID, int targetbodyID, int targetxpos, int targetypos, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,4).Add(eatType).Add(playerId).Add(bodyId).Add(xpos).Add(ypos).Add(TargetPlayerID).Add(targetbodyID).Add(targetxpos).Add(targetypos).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_ComposeBody(Robot r, int[] list, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,7).Add(list).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_EnterRoom(Robot r, int roomID, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,1).Add(roomID).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_ThornMove(Robot r, int[] list, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,5).Add(list).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_MoveBody(Robot r, int playerID, int[] list, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,3).Add(playerID).Add(list).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_SplitBody(Robot r, int playerID, int m_xpos, int m_ypos, int[] list, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,6).Add(playerID).Add(m_xpos).Add(m_ypos).Add(list).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_CreateRoom(Robot r, int arg, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,2).Add(arg).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_removePlayer(Robot r, int targetID, int teamID)
	{
		r.GetSendBuffer().Clear().AddID(1,20).Add(targetID).Add(teamID).Send(r.GetLink());
	}

	public static void RoomImpl_rebirth(Robot r, int playerId, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,22).Add(playerId).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_SplitQiu(Robot r, int playerID, int[] list, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,17).Add(playerID).Add(list).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_LeftMatch(Robot r, long playerId, int teamID, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,31).Add(playerId).Add(teamID).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_speaking(Robot r, String talking)
	{
		r.GetSendBuffer().Clear().AddID(1,38).Add(talking).Send(r.GetLink());
	}

	public static void RoomImpl_SplitQiuPlace(Robot r, int qiuId, int playerId, int xpos, int ypos, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,19).Add(qiuId).Add(playerId).Add(xpos).Add(ypos).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_chooseTeam(Robot r, int teamID, int teamName)
	{
		r.GetSendBuffer().Clear().AddID(1,33).Add(teamID).Add(teamName).Send(r.GetLink());
	}

	public static void RoomImpl_EatFood(Robot r, int bodyID, int score, long time)
	{
		r.GetSendBuffer().Clear().AddID(1,13).Add(bodyID).Add(score).Add(time).Send(r.GetLink());
	}

	public static void RoomImpl_gameStart(Robot r, int roomID)
	{
		r.GetSendBuffer().Clear().AddID(1,29).Add(roomID).Send(r.GetLink());
	}

	public static void RoomImpl_leaveRoom(Robot r, int roomID)
	{
		r.GetSendBuffer().Clear().AddID(1,36).Add(roomID).Send(r.GetLink());
	}

	public static void RoomImpl_getFriendList(Robot r, int needChange)
	{
		r.GetSendBuffer().Clear().AddID(1,34).Add(needChange).Send(r.GetLink());
	}

	public static void RoomImpl_visitGame(Robot r, long roleID)
	{
		r.GetSendBuffer().Clear().AddID(1,37).Add(roleID).Send(r.GetLink());
	}

	public static void RoomImpl_dissolution(Robot r, int roomID)
	{
		r.GetSendBuffer().Clear().AddID(1,35).Add(roomID).Send(r.GetLink());
	}

	public static void CenterImpl_EnterFreeRoomCenter(Robot r, int isEnter, int isTeam)
	{
		r.GetSendBuffer().Clear().AddID(3,14).Add(isEnter).Add(isTeam).Send(r.GetLink());
	}

	public static void CenterImpl_InvitationFriend(Robot r, long p_ID, int p_teamID, long m_friendID, int roomID, int ifFree)
	{
		r.GetSendBuffer().Clear().AddID(3,3).Add(p_ID).Add(p_teamID).Add(m_friendID).Add(roomID).Add(ifFree).Send(r.GetLink());
	}

	public static void CenterImpl_creatRoomRule(Robot r, int isTeam, int gameTime, int teNumber, int eachSize, String roomName, int roomPass)
	{
		r.GetSendBuffer().Clear().AddID(3,13).Add(isTeam).Add(gameTime).Add(teNumber).Add(eachSize).Add(roomName).Add(roomPass).Send(r.GetLink());
	}

	public static void CenterImpl_visitList(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,6).Send(r.GetLink());
	}

	public static void CenterImpl_TeamGameMatch(Robot r, int p_teamID)
	{
		r.GetSendBuffer().Clear().AddID(3,10).Add(p_teamID).Send(r.GetLink());
	}

	public static void CenterImpl_CreatTeam(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(3,1).Send(r.GetLink());
	}

	public static void CenterImpl_visitPlayer(Robot r, int roomID)
	{
		r.GetSendBuffer().Clear().AddID(3,8).Add(roomID).Send(r.GetLink());
	}

	public static void CenterImpl_RetreatTeam(Robot r, long roleID, int p_teamID)
	{
		r.GetSendBuffer().Clear().AddID(3,12).Add(roleID).Add(p_teamID).Send(r.GetLink());
	}

	public static void CenterImpl_JoinTeam(Robot r, int p_teamID, long m_friendID)
	{
		r.GetSendBuffer().Clear().AddID(3,4).Add(p_teamID).Add(m_friendID).Send(r.GetLink());
	}

	public static void CenterDateImpl_getClass(Robot r)
	{
		r.GetSendBuffer().Clear().AddID(7,1).Send(r.GetLink());
	}

	public static void CenterDateImpl_sendMessage(Robot r, long targetID, String message)
	{
		r.GetSendBuffer().Clear().AddID(7,8).Add(targetID).Add(message).Send(r.GetLink());
	}

	public static void CenterDateImpl_addFriend(Robot r, long p_user)
	{
		r.GetSendBuffer().Clear().AddID(7,2).Add(p_user).Send(r.GetLink());
	}

	public static void CenterDateImpl_buyShopping(Robot r, int p_user, int giftID)
	{
		r.GetSendBuffer().Clear().AddID(7,6).Add(p_user).Add(giftID).Send(r.GetLink());
	}

	public static void CenterDateImpl_deleteFriends(Robot r, long targetID)
	{
		r.GetSendBuffer().Clear().AddID(7,4).Add(targetID).Send(r.GetLink());
	}

	public static void CenterDateImpl_givePresent(Robot r, long user, int p, int p, String this)
	{
		r.GetSendBuffer().Clear().AddID(7,5).Add(user).Add(p).Add(p).Add(this).Send(r.GetLink());
	}

	public static void CenterDateImpl_changeSkin(Robot r, int list, int buffer)
	{
		r.GetSendBuffer().Clear().AddID(7,10).Add(list).Add(buffer).Send(r.GetLink());
	}

	public static void CenterDateImpl_agreeAdd(Robot r, long p_user)
	{
		r.GetSendBuffer().Clear().AddID(7,3).Add(p_user).Send(r.GetLink());
	}

	public static void CenterDateImpl_userHome(Robot r, long p)
	{
		r.GetSendBuffer().Clear().AddID(7,7).Add(p).Send(r.GetLink());
	}

	public static void CenterDateImpl_rankingClass(Robot r, int this, int p_user, int list_type)
	{
		r.GetSendBuffer().Clear().AddID(7,9).Add(this).Add(p_user).Add(list_type).Send(r.GetLink());
	}

	public static void CenterDateImpl_searchUser(Robot r, String this)
	{
		r.GetSendBuffer().Clear().AddID(7,11).Add(this).Send(r.GetLink());
	}

	public static void CenterDateImpl_rankingPerson(Robot r, int list_type, int number, int size)
	{
		r.GetSendBuffer().Clear().AddID(7,12).Add(list_type).Add(number).Add(size).Send(r.GetLink());
	}

}
