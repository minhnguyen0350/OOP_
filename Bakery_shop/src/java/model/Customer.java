package model;

public class Customer {
    private int id;          // customerId
    private String name;     // customerName
    private String email;    // email
    private String tel;      // tel (số điện thoại)
    private String address;  // địa chỉ giao bánh

    // Constructor đầy đủ
    public Customer(int id, String name, String email, String tel, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.address = address;
    }

    // Constructor khi thêm mới khách (chưa có id)
    public Customer(String name, String email, String tel, String address) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.address = address;
    }

    // Getter - Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer: " + id +
               ", Name=" + name +
               ", Email=" + email +
               ", Tel=" + tel +
               ", Address=" + address;
    }
}

