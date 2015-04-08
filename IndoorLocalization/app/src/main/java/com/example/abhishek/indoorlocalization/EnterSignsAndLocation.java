package com.example.abhishek.indoorlocalization;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * Created by ABHISHEK on 02-04-2015.
 */
public class EnterSignsAndLocation {

    public static List<HashMap<String,String>> mList = new ArrayList<>();
    HashMap<String,String> mFloor1Details = new HashMap<>();
    HashMap<String,String> mFloor2Details = new HashMap<>();
    HashMap<String,String> mFloor3Details = new HashMap<>();
    HashMap<String,String> mFloor4Details = new HashMap<>();

    public void fill_data(){
        mFloor1Details.put("AAAA","1111");
        mFloor1Details.put("AABB","2222");
        mFloor2Details.put("CCCC","3333");
        mFloor2Details.put("CCDD","4444");
        mFloor3Details.put("EEEE","5555");
        mFloor3Details.put("EEFF","6666");
        mFloor4Details.put("GGGG","7777");
        mFloor4Details.put("GGHH","8888");
        mList.add(mFloor1Details);
        mList.add(mFloor2Details);
        mList.add(mFloor3Details);
        mList.add(mFloor4Details);
    }
}
