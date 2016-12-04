package Other;

/**
 * Created by Scynet on 25.11.2016.
 */
public class Player {
    String usename;
    String pass;
    String email;
    int agent;
    String fname;
    String lname;
    String city;
    int country;
    String adress;

    public void setEmail(String email) {
        this.email = email;
    }

    int phone;
    int gender;

    public String getPass() {
        return pass;
    }


    public String getEmail() {
        return email;
    }

    public String getUsename() {
        return usename;
    }

    public int getAgent() {
        return agent;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCity() {
        return city;
    }

    public int getCountry() {
        return country;
    }



    public String getAdress() {
        return adress;
    }

    public int getPhone() {
        return phone;
    }

    public int getGender() {
        return gender;
    }

    public Player(int maxagent,int maxcountry,int length){
        Randomizer rs = new Randomizer();
        usename=rs.generate(length);
        pass=rs.generate(length);
        email=rs.generate(length).concat("@mail.ru");
        fname=rs.generate(length);
        lname=rs.generate(length);
        city=rs.generate(length);
        agent=rs.randInt(1,maxagent);
        country=rs.randInt(1,maxcountry);
        adress=rs.generate(length);
        phone=rs.randInt(11111111,99999999);
        gender=rs.randInt(1,2);


    }
    public Player(int maxagent,int maxcountry,String prevname,int length){
        Randomizer rs = new Randomizer();
        usename=prevname;
        email=rs.generate(length).concat("@mail.ru");
        fname=rs.generate(length);
        lname=rs.generate(length);
        city=rs.generate(length);
        agent=rs.randInt(1,maxagent);
        country=rs.randInt(1,maxcountry);
        adress=rs.generate(length);
        phone=rs.randInt(11111111,99999999);
        gender=rs.randInt(1,2);

    }
}
