package logic;

public enum eErrorCode {
    Error_1, //房間加入失敗
    Error_2, // 队伍加入失败,隊伍人滿
    Error_3, // 房间的人数满了;
    Error_4, //登录失败密码错误;
    Error_5, // 有人登录相同账户登录失败;
    Error_6;

    public int ID() {
        switch (this) {
            case Error_1:
                return 1;
            case Error_2:
                return 2;
            case Error_3:
                return 3;
            case Error_4:
                return 4;
            case Error_5:
                return 5;
            case Error_6:
                return 6;
        }
        return 0;
    }
}