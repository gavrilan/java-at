package ru.agavrilova.istore.user;

public class Buyer extends User implements Comparable<Buyer>{
    private String id;
    private String fullName;
    private String phoneNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Buyer(String id, String fullName, String login, String password, String phoneNum) {
        super(login, password);
        this.id = id;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", login='" + this.getLogin() + '\'' +
                '}';
    }

    @Override
    public int compareTo(Buyer o) {
        return this.getFullName().compareTo(o.getFullName());
    }
}
