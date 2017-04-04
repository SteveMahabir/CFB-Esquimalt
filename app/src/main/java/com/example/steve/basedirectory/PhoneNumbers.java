package com.example.steve.basedirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steve on 29/03/2017.
 */

class PhoneNumbers {

    /*                  MCDV's                  */
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

    public ArrayList<String[]> GlobalPhoneList;


    PhoneNumbers(){
        GlobalPhoneList.add(HMCS_BRANDON);


        GlobalPhoneList.add(HMCS_EDMONTON);
        GlobalPhoneList.add(HMCS_SASKATOON);
        GlobalPhoneList.add();
    }


}