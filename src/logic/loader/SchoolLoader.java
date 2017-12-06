package logic.loader;

import core.DBLoaderEx;
import core.DBMgr;
import logic.userdata.zz_huiyuan;
import logic.userdata.zz_school;
import logic.userdata.zz_school;
import sun.security.jca.GetInstance;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SchoolLoader {

public static SchoolLoader instance;

    public static SchoolLoader getInstance() {
        if(instance==null){
            instance=new SchoolLoader();
        }
        return instance;
    }


    public SchoolLoader() {
    }

    public String getSchool(int id) {

        zz_school[] strings = DBMgr.ReadSQL(new zz_school(), "select * from zz_school where id=" + id);
        for (zz_school string : strings) {
            return string.SchoolName.Get();
        }

        return "未知֪";
    }

}
