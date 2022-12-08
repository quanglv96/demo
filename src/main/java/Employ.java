public class Employ {
    private int STT;
    private int id_employment;
    private String name_employment;
    private String address;
    private String email;
    private String phone;
    private double salary;
    private Depart department;

    public Employ(int STT, int id_employment, String name_employment, String address, String email, String phone, double salary, Depart department) {
        this.STT = STT;
        this.id_employment = id_employment;
        this.name_employment = name_employment;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }

    public Employ() {
    }

    public Employ(String name_employment, String address, String email, String phone, double salary, Depart department) {
        this.name_employment = name_employment;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }

    public Employ(int id_employment, String name_employment, String address, String email, String phone, double salary, Depart department) {
        this.id_employment = id_employment;
        this.name_employment = name_employment;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.department = department;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public int getId_employment() {
        return id_employment;
    }

    public void setId_employment(int id_employment) {
        this.id_employment = id_employment;
    }

    public String getName_employment() {
        return name_employment;
    }

    public void setName_employment(String name_employment) {
        this.name_employment = name_employment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Depart getDepartment() {
        return department;
    }

    public void setDepartment(Depart department) {
        this.department = department;
    }
}
