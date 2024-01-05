package DiaLogApp;

import java.io.Serializable;

public class Patient implements Serializable{
    protected String name;
    protected int id;
    protected String phoneNum;
    public Patient(String name, int id, String phoneNum){
        this.name = name;
        this.id = id;
        this.phoneNum = phoneNum;
    }
    public String getBedPhoneNum(){
        return phoneNum;
    }
    public String getName(){
        return name;
    }

    public void setBedPhoneNum(String bedPhoneNum){
        Integer.parseInt(bedPhoneNum);
        phoneNum = bedPhoneNum;
    }

    //Overrides contact inPerson
    public void contact(String msg){
        System.out.println("Contact to DiaLogApp.Patient via msg:");
        System.out.println(msg);
    }
}
