package com.jwyming.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ConfigureInfo {

    @Value("#{configProperties['Query.VehicleURL']}")
    private static String QueryVehicleURL;

    @Value("#{configProperties['Query.AccountURL']}")
    private static String QueryAccountURL;

    @Value("#{configProperties['Query.OrderURL']}")
    private static String QueryOrderURL;

    @Value("#{configProperties['Query.MBIIS']}")
    private static String QueryMBIISURL;

    @Value("#{configProperties['Cancel.AccountURL']}")
    private static String CancelAccountURL;

    @Value("#{configProperties['CustomerServiceEndPoint']}")
    private static String CustomerServiceEndPoint;

    @Value("#{configProperties['Config.MBIIS']}")
    private static String ConfigMBIISURL;

    @Value("#{configProperties['Source.Name']}")
    private static String SourceName;

    @Value("#{configProperties['Close.Internet.Flag']}")
    private static String closeInternetFlag;

    @Value("#{configProperties['Vehicle.trial.end.date']}")
    private static String vehicleTrialEndDate;

    @Value("#{configProperties['Config.SendFreeText2CarURL']}")
    private static String sendFreeText2CarURL;

    @Value("#{configProperties['Balance.Compensation.Day']}")
    private static String balanceCompensationDay;

    public static String getSourceName() {
        return SourceName;
    }

    public static void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    private static ApplicationContext appContext;

    public static String getQueryOrderURL() {
        return QueryOrderURL;
    }

    public static void setQueryOrderURL(String queryOrder) {
        QueryOrderURL = queryOrder;
    }

    public static void setQueryVehicleURL(String queryVehicleURL) {
        QueryVehicleURL = queryVehicleURL;
    }

    public static void setQueryAccountURL(String queryAccountURL) {
        QueryAccountURL = queryAccountURL;
    }

    public static String getQueryVehicleURL() {
        return QueryVehicleURL;
    }

    public static String getQueryAccountURL() {
        return QueryAccountURL;
    }

    public static String getQueryMBIISURL() {
        return QueryMBIISURL;
    }

    public static String getConfigMBIISURL() {
        return ConfigMBIISURL;
    }

    public static void setConfigMBIISURL(String configMBIISURL) {
        ConfigMBIISURL = configMBIISURL;
    }

    public static void setQueryMBIISURL(String queryMBIISURL) {
        QueryMBIISURL = queryMBIISURL;
    }

    public static String getCancelAccountURL() {
        return CancelAccountURL;
    }

    public static void setCancelAccountURL(String cancelAccountURL) {
        CancelAccountURL = cancelAccountURL;
    }

    public static String getCustomerServiceEndPoint() {
        return CustomerServiceEndPoint;
    }

    public static void setCustomerServiceEndPoint(String customerServiceEndPoint) {
        CustomerServiceEndPoint = customerServiceEndPoint;
    }

    public static String getCloseInternetFlag() {
        return closeInternetFlag;
    }

    public static void setCloseInternetFlag(String closeInternetFlag) {
        ConfigureInfo.closeInternetFlag = closeInternetFlag;
    }

    public static String getVehicleTrialEndDate() {
        return vehicleTrialEndDate;
    }

    public static void setVehicleTrialEndDate(String vehicleTrialEndDate) {
        ConfigureInfo.vehicleTrialEndDate = vehicleTrialEndDate;
    }

    static {

        appContext = new ClassPathXmlApplicationContext(
                "classpath:/ws_env/beanProperties.xml");

        appContext.getBean(ConfigureInfo.class);

        // QueryVehicleURL = ConfigureInfo.getQueryAccountURL();
        // appContext.getBean(ConfigureInfo.class);
        // QueryAccountURL = ConfigureInfo.getQueryAccountURL();
        // appContext.getBean(ConfigureInfo.class);
        // QueryOrderURL = ConfigureInfo.getQueryOrderURL();

    }

    public static String getSendFreeText2CarURL() {
        return sendFreeText2CarURL;
    }

    public static void setSendFreeText2CarURL(String sendFreeText2CarURL) {
        ConfigureInfo.sendFreeText2CarURL = sendFreeText2CarURL;
    }

    public static String getBalanceCompensationDay() {
        return balanceCompensationDay;
    }

    public static void setBalanceCompensationDay(String balanceCompensationDay) {
        ConfigureInfo.balanceCompensationDay = balanceCompensationDay;
    }
}
