package com.example.steve.basedirectory;

import java.util.ArrayList;


class Directory {

    // Categories
    Category YJetty;
    Category HealthCare;
    Category BaseServices;

    /*                  MCDV's                  */
    public ArrayList<Unit> YJettyGroup = new ArrayList<>();
    static String HMCS_BRANDON = "tel:2503637472";
    static String HMCS_NANAIMO = "tel:2503635712";
    static String HMCS_WHITEHORSE = "tel:2503631040";
    static String HMCS_YELLOWKNIFE = "tel:2503632467";
    static String HMCS_EDMONTON= "tel:2503637940";
    static String HMCS_SASKATOON = "tel:2503637459";

    /*                  Health Care             */
    public ArrayList<Unit> HealthCareGroup = new ArrayList<>();
    static String PHARMACY_FRONT_DESK =  "tel:2503634476";
    static String PHARMACY_MIR =  "tel:2503634143";
    static String MEDICAL = "tel:2503635641";
    static String CDU1 = "tel:2503634120";
    static String CDU2 = "tel:2503635641";
    static String CDU3 = "tel:2503635646";

    /*              Base Services               */
    public ArrayList<Unit> BaseServicesGroup = new ArrayList<>();
    static String QHM = "tel:2503632160";
    static String NRCC_MAIN = "tel:2503634195";
    static String NRCC_PAY = "tel:2503634171";
    static String BASE_TAXI = "tel:2503632384";
    static String POST_OFFICE = "tel:2503632176";
    static String CLOTHING_STORES = "tel:2503634947";


    // Default Constructor
    Directory(){
        //Category YJetty; = new Category("Y Jetty", 1);
        //Category HealthCare; = new Category("Health Care", 1);
        //Category BaseServices; = new Category("Base Services", 1);
    }

    public void SetupData() {
        YJettyGroup.add(new Unit("HMCS BRANDON", HMCS_BRANDON, R.drawable.brandon, YJetty));
        YJettyGroup.add(new Unit("HMCS NANAIMO",HMCS_NANAIMO, R.drawable.nanaimo, YJetty));
        YJettyGroup.add(new Unit("HMCS WHITEHORSE",HMCS_WHITEHORSE,R.drawable.whitehorse,YJetty));
        YJettyGroup.add(new Unit("HMCS YELLOWKNIFE",HMCS_YELLOWKNIFE,R.drawable.yellowknife,YJetty));
        YJettyGroup.add(new Unit("HMCS EDMONTON",HMCS_EDMONTON,R.drawable.edmonton,YJetty));
        YJettyGroup.add(new Unit("HMCS SASKATOON",HMCS_SASKATOON,R.drawable.saskatoon,YJetty));

        HealthCareGroup.add(new Unit("Pharmacy Front Desk",PHARMACY_FRONT_DESK,R.drawable.medical_service, HealthCare));
        HealthCareGroup.add(new Unit("Pharmacy (MIR)",PHARMACY_MIR,R.drawable.medical_service, HealthCare));
        HealthCareGroup.add(new Unit("Medical",MEDICAL,R.drawable.medical_service, HealthCare));
        HealthCareGroup.add(new Unit("CDU 1",CDU1,R.drawable.medical_service, HealthCare));
        HealthCareGroup.add(new Unit("CDU 2",CDU2,R.drawable.medical_service, HealthCare));
        HealthCareGroup.add(new Unit("CDU 3",CDU3,R.drawable.medical_service, HealthCare));

        BaseServicesGroup.add(new Unit("QHM",QHM,R.drawable.cfb_esquimalt,BaseServices));
        BaseServicesGroup.add(new Unit("NRCC (Main)",NRCC_MAIN,R.drawable.cfb_esquimalt,BaseServices));
        BaseServicesGroup.add(new Unit("NRCC (Pay)",NRCC_PAY,R.drawable.cfb_esquimalt,BaseServices));
        BaseServicesGroup.add(new Unit("Base Taxi",BASE_TAXI,R.drawable.cfb_esquimalt,BaseServices));
        BaseServicesGroup.add(new Unit("Post Office",POST_OFFICE,R.drawable.cfb_esquimalt,BaseServices));
        BaseServicesGroup.add(new Unit("Clothing Stores",CLOTHING_STORES,R.drawable.cfb_esquimalt,BaseServices));
    }


}