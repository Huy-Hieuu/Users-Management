package API_Demo.Springboot.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    private Long id;
    @Column(name = "_productName")
    private String productName;
    @Column(name = "_year")
    private int year;
    @Column(name = "_price")
    private Double price;
    @Column(name = "_url")
    private String url;

    //default constructor
    public Product(){}
    public Product(String productName, int year, Double price, String url)
    {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getId()
    {
        return this.id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return year == product.year && id.equals(product.id) && productName.equals(product.productName) && price.equals(product.price) && Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, year, price, url);
    }
}

