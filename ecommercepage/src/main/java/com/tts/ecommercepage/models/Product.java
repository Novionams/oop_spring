package com.tts.ecommercepage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quanity; 

    private float price; 

    private String description; 

    private String name; 

    private String brand; 

    private String category; 
    
    private String imageURL;

   
    public Product() {
    }

    public Product(Long id, int quanity, float price, String description, String name, String brand, String category,
    String imageURL) {
this.id = id;
this.quanity = quanity;
this.price = price;
this.description = description;
this.name = name;
this.brand = brand;
this.category = category;
this.imageURL = imageURL;
}




    public Long getId() {
        return id;
    }


    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

   
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Product [brand=" + brand + ", category=" + category + ", description=" + description + ", id=" + id
                + ", imageURL=" + imageURL + ", name=" + name + ", price=" + price + ", quanity=" + quanity + "]";
    }



   

    
    

    
    

	
    
    

    


    

 


    

    




   

  
    

	
}
