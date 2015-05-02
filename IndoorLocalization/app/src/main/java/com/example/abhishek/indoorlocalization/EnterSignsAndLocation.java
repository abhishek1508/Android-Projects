package com.example.abhishek.indoorlocalization;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

/**
 * Created by ABHISHEK on 02-04-2015.
 */
public class EnterSignsAndLocation {

    public static List<HashMap<String,String>> mList = new ArrayList<>();
    HashMap<String,String> mFloor2Details = new HashMap<>();
    HashMap<String,String> mFloor6Details = new HashMap<>();

    public void fill_data(){
        mFloor2Details.put("199G, Entrance 3rd Street","847,809");
        mFloor2Details.put("Exit (near Entrance 199G)","847,809");
        mFloor2Details.put("Exit (near 123, Lieber Room)","800,738");
        mFloor2Details.put("123, Lieber Room","776,805");
        mFloor2Details.put("121, Delph Lab","706,749");
        mFloor2Details.put("Exit (near 121, Delph Lab)","725,623");
        mFloor2Details.put("120, Development Office","725,661");
        mFloor2Details.put("116","672,678");
        mFloor2Details.put("119","592,750");
        mFloor2Details.put("113","508,678");
        mFloor2Details.put("115, Womens Restroom","414,660");
        mFloor2Details.put("117","486,765");
        mFloor2Details.put("Sign- Medical Sciences (near back Entrance)","458,684");
        mFloor2Details.put("Exit (near 115, Womens Restroom)","478,683");
        mFloor2Details.put("Exit (near 111,Conference Room)","523,641");
        mFloor2Details.put("Sign- Medical Sciences (near 111, Conference Room)","523,641");
        mFloor2Details.put("111, Conference Room","497,645");
        mFloor2Details.put("109","485,610");
        mFloor2Details.put("110","600,615");
        mFloor2Details.put("112","595,645");
        mFloor2Details.put("108","585,590");
        mFloor2Details.put("104, Med Sciences Program Office","591,540");
        mFloor2Details.put("107","486,555");
        mFloor2Details.put("105, Directors Office","497,503");
        mFloor2Details.put("102, Alumni Room, Conference Room","571,441");
        mFloor2Details.put("103","462,460");
        mFloor2Details.put("101","462,363");//423
        mFloor2Details.put("Sign- Medical Sciences (near 101)","548,397");
        mFloor2Details.put("Exit (near Northwest Entrance)","548,397");
        mFloor2Details.put("Sign- Medical Sciences (near A100)","615,364");
        mFloor2Details.put("A100","706,517");
        mFloor2Details.put("A100A","731,394");
        mFloor2Details.put("Exit (near A199, Cafeteria, Seating Area)","781,364");
        mFloor2Details.put("122A","836,500");
        mFloor2Details.put("Exit (near 122A)","780,500");
        mFloor2Details.put("A199G, Elevator","730,608");
        mFloor2Details.put("A194","691,587");
        mFloor2Details.put("Stair (near A194)","691,587");
        mFloor2Details.put("Exit (near Undergraduate Advising Office Sign)","699,610");
        mFloor2Details.put("Sign- Undergraduate Advising Office A115","746,670");
        mFloor2Details.put("Sign- JA100","746,670");
        mFloor2Details.put("122","836,600");
        mFloor2Details.put("124, JH 124","910,635");
        mFloor2Details.put("127, Department of Biology","917,750");
        mFloor2Details.put("127D, Payroll","966,743");
        mFloor2Details.put("126","890,670");
        mFloor2Details.put("128","1008,662");
        mFloor2Details.put("129, Computing Support, JH 129 Biology Computing Support Office","1027,735");
        mFloor2Details.put("130","1027,665");
        mFloor2Details.put("131","1074,745");
        mFloor2Details.put("133, Martins Lab","1249,737");
        mFloor2Details.put("143","1175,666");
        mFloor2Details.put("141, Mens Restroom","1272,662");
        mFloor2Details.put("Exit (Near 141, Men Restroom)","1230,690");
        mFloor2Details.put("Sign- To Room 065 (near 141, Men Restroom)","1230,690");
        mFloor2Details.put("145","1191,637");
        mFloor2Details.put("Sign- Stair (near 145)","1130,653");
        mFloor2Details.put("Exit (near 143 & 145)","1130,677");
        mFloor2Details.put("Sign- Biology Office 142 (near 145)","1130,677");
        mFloor2Details.put("147, Womens Restroom","1220,615");
        mFloor2Details.put("132","1110,628");
        mFloor2Details.put("134","1103,532");
        mFloor2Details.put("149, Berner Lab, Schultz Lab","1222,549");
        mFloor2Details.put("136","1099,465");
        mFloor2Details.put("155, Reynold Lab","1222,457");
        mFloor2Details.put("Sign- Exit (near Room 155)","1164,410");
        mFloor2Details.put("Sign- Room 124 (near Room 155) ","1164,410");
        mFloor2Details.put("159","1233,303");
        mFloor2Details.put("138","1079,430");
        mFloor2Details.put("142B","1122,250");
        mFloor2Details.put("Exit (near 142B)","1156,271");
        mFloor2Details.put("Sign- First Floor (near 138), Martins Lab","1119, 385");
        mFloor2Details.put("Sign- Stair(near 138)","1119,395");
        mFloor2Details.put("Sign- Exit (near 138, northeast Entrance)","1119,395");
        mFloor2Details.put("Sign- Stair (near NorthEast Entrance)","1156,271");
        mFloor2Details.put("Sign- 142, Departmental Office","3333");
        mFloor2Details.put("A199K, Service Elevator","1012,327");
        mFloor2Details.put("A198","1033,322");
        mFloor2Details.put("A197","1033,303");
        mFloor2Details.put("A106","1020,387");
        mFloor2Details.put("A105","945,411");
        mFloor2Details.put("Sign- Exit (near A105)","956,361");
        mFloor2Details.put("Sign- Undergraduate Advising Office A115 (near A105)","956,361");
        mFloor2Details.put("Sign- Rooms A107, A109, A110, A115 (near A107)","962,336");
        mFloor2Details.put("A107","1012,283");
        mFloor2Details.put("A108","996,242");
        mFloor2Details.put("Sign- Exit(near A108)","962,252");
        mFloor2Details.put("Sign- Stair (near A108 and Exit)","962,210");
        mFloor2Details.put("A109, L113","1044,172");
        mFloor2Details.put("A110, Q201 ","966,97");
        mFloor2Details.put("A111","904,177");
        mFloor2Details.put("A112, James Hengeveld Biology L113","828,110");//127
        mFloor2Details.put("A113","828,151");
        mFloor2Details.put("A114","828,171");
        mFloor2Details.put("A115, Biology Undergraduate Advisor","828,223");
        mFloor2Details.put("A104, L113","903,278");
        mFloor2Details.put("A196A, Mens Restroom","889,380");
        mFloor2Details.put("A195A, Womens Restroom","837,380");
        mFloor2Details.put("A103","839,325");
        mFloor2Details.put("Sign- Exit (near A103 & A199)","833,345");
        mFloor2Details.put("A199,Life Sciences Library","");
        mFloor2Details.put("A199, Cafeteria","705,293");
        mFloor2Details.put("Sign- Jordan Hall A302 (near A199, Cafeteria)","823,317");
        mFloor2Details.put("Sign- South Stair 1 (near 123, Lieber Room)","850,645");


        mFloor6Details.put("SouthEast Stair Roof Access","1546,900");
        mFloor6Details.put("598","1474,900");
        mFloor6Details.put("513","1440,942");
        mFloor6Details.put("599G","1331,896");
        mFloor6Details.put("517","1428,844");
        mFloor6Details.put("519","1492,818");
        mFloor6Details.put("512","1370,700");
        mFloor6Details.put("523","1494,720");
        mFloor6Details.put("515","1532,955");
        mFloor6Details.put("508","1194,890");
        mFloor6Details.put("510","1380,782");
        mFloor6Details.put("A599J","800,760");//check again
        mFloor6Details.put("Sign- Stair near Room A594","765,780");
        mFloor6Details.put("A504","848,540");//check again
        mFloor6Details.put("A503, INGEN Drosophilla Core Facility","847,680");
        mFloor6Details.put("A501","877,777");
        mFloor6Details.put("Exit (near A501)","909,730");//check again
        mFloor6Details.put("A502","730,650");
        mFloor6Details.put("A502A","845,591");
        mFloor6Details.put("A505","1002,560");
        mFloor6Details.put("A506, FlyBase","1011,376");
        mFloor6Details.put("A515","964,408");
        mFloor6Details.put("A517","964,408");
        mFloor6Details.put("A595, Women Restroom","950,480");
        mFloor6Details.put("A596, Men Restroom","1040,477");
        mFloor6Details.put("A507","1124,480");
        mFloor6Details.put("A505C","1083,553");
        mFloor6Details.put("Exit (near A508)","1156,448");
        mFloor6Details.put("A508","1174,480");
        mFloor6Details.put("A509","1228,474");
        mFloor6Details.put("Exit (near A509)","1193,408");
        mFloor6Details.put("A599G, Service Elevator","1193,388");//438
        mFloor6Details.put("A598B","1243,474");
        mFloor6Details.put("A597","1243,360");
        mFloor6Details.put("A510A","1065,360");
        mFloor6Details.put("A510","1065,360");
        mFloor6Details.put("A510B","1065,360");
        mFloor6Details.put("A511","1226,280");//340
        mFloor6Details.put("A513","1069,330");
        mFloor6Details.put("A512","1164,239");//309
        mFloor6Details.put("A514","1062,208");//268
        mFloor6Details.put("Exit (near A514)","1106,296");
        mFloor6Details.put("Sign- Stair (near A514)","1106,206");//296
        mFloor6Details.put("NorthEast Stair Roof Access 5","1106,296");
        mFloor6Details.put("Sign- 5a","1106,296");//same as roof access sign*/

        mList.add(mFloor2Details);
        mList.add(mFloor6Details);
    }
}
