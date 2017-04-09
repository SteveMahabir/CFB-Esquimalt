package com.example.steve.basedirectory;

import java.util.ArrayList;
import java.util.List;


// Middle Class used to populate the Initial Database
class Directory {

    /*                  MCDV's                  */
    public ArrayList<String[]> YJetty = new ArrayList<>();
    static Catagory YJetty = new Catagory("YJetty", 1);
    static String HMCS_BRANDON = "tel:2503637472";
    static String HMCS_NANAIMO = "tel:2503635712";
    static String HMCS_WHITEHORSE = "tel:2503631040";
    static String HMCS_YELLOWKNIFE = "tel:2503632467";
    static String HMCS_EDMONTON= "tel:2503637940";
    static String HMCS_SASKATOON = "tel:2503637459";

    /*                  Health Care             */

    static String PHARMACY_FRONT_DESK =  "tel:2503634476";
    static String PHARMACY_MIR =  "tel:2503634143";
    static String MEDICAL = "tel:2503635641";
    static String CDU1 = "tel:2503634120";
    static String CDU2 = "tel:2503635641";
    static String CDU3 = "tel:2503635646";

    /*              Base Services               */

    static String QHM = "tel:2503632160";
    static String NRCC_MAIN = "tel:2503634195";
    static String NRCC_PAY = "tel:2503634171";
    static String BASE_TAXI = "tel:2503632384";
    static String POST_OFFICE = "tel:250363-2176";
    static String CLOTHING_STORES = "tel:2503634947";



    public ArrayList<String[]> HealthCare = new ArrayList<>();
    public ArrayList<String[]> BaseServices = new ArrayList<>();

    Directory(){
        YJetty.add(new String[]{"HMCS BRANDON",HMCS_BRANDON});
        YJetty.add(new String[]{"HMCS NANAIMO",HMCS_NANAIMO});
        YJetty.add(new String[]{"HMCS WHITEHORSE",HMCS_WHITEHORSE});
        YJetty.add(new String[]{"HMCS YELLOWKNIFE",HMCS_YELLOWKNIFE});
        YJetty.add(new String[]{"HMCS EDMONTON",HMCS_EDMONTON});
        YJetty.add(new String[]{"HMCS SASKATOON",HMCS_SASKATOON});

        HealthCare.add(new String[]{"Pharmacy Front Desk",PHARMACY_FRONT_DESK});
        HealthCare.add(new String[]{"Pharmacy (MIR)",PHARMACY_MIR});
        HealthCare.add(new String[]{"Medical",MEDICAL});
        HealthCare.add(new String[]{"CDU 1",CDU1});
        HealthCare.add(new String[]{"CDU 2",CDU2});
        HealthCare.add(new String[]{"CDU 3",CDU3});

        BaseServices.add(new String[]{"QHM",QHM});
        BaseServices.add(new String[]{"NRCC (Main)",NRCC_MAIN});
        BaseServices.add(new String[]{"NRCC (Pay)",NRCC_PAY});
        BaseServices.add(new String[]{"Base Taxi",BASE_TAXI});
        BaseServices.add(new String[]{"Post Office",POST_OFFICE});
        BaseServices.add(new String[]{"Clothing Stores",CLOTHING_STORES});
    }


}