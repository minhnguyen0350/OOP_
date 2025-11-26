package model;

import java.util.ArrayList;

public class Banh {

    private int banhId;             // mã bánh
    private int rate;               // số sao
    private String name;            // tên bánh
    private String description;     // mô tả ngắn
    private String detail;          // mô tả chi tiết
    private float price;            // giá gốc
    private float sale;             // giảm giá (%)
    private ArrayList<String> images;   // danh sách URL ảnh

    public Banh(int banhId, String name, float price,
                float sale, ArrayList<String> images,
                int rate, String description, String detail) {

        this.banhId = banhId;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.images = images;
        this.rate = rate;
        this.description = description;
        this.detail = detail;
    }

    // ---------------- GETTER - SETTER ------------------

    public int getId() {
        return banhId;
    }

    public void setId(int id) {
        this.banhId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Format giá thành dạng 200.000
    public String getFormattedPrice() {
        String result = "";
        String s = "" + (int) this.price;
        int count = 1;

        for (int i = s.length() - 1; i >= 0; i--) {
            result = s.charAt(i) + result;
            if (count % 3 == 0 && i != 0) result = "." + result;
            count++;
        }
        return result;
    }

    public float getRawPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

    // Giá sau khi áp dụng giảm giá
    public float getFinalPrice() {
        return price * (1 - sale / 100);
    }

    // Lấy ảnh đầu tiên
    public String getMainImage() {
        return (images != null && images.size() > 0) ? images.get(0) : "";
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return banhId + " - " + name + " - Giá: " + price + " - Giảm: " + sale + "%";
    }
}
