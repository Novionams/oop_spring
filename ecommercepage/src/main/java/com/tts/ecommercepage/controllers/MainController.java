package com.tts.ecommercepage.controllers;

import java.util.ArrayList;
import java.util.List;

import com.tts.ecommercepage.models.Product;
import com.tts.ecommercepage.models.User;
import com.tts.ecommercepage.services.ProductService;
import com.tts.ecommercepage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
public class MainController {
    @Autowired
    ProductService productService;

    public void addNew() {
List<Product> allProducts = productService.findAll();

if(allProducts.isEmpty()) {
    List<Product> newProducts = new ArrayList<Product>();

    newProducts.add(new Product(null, 1,(float) 50.00, "Crown Ring", "Silver ring", "Kendra Scott", "Ring",  "image/Kendrascottcrown.jpg"));

    newProducts.add(new Product(null, 1, (float) 150.00, "Gold trim hoop earings with silver lattice", "Drop Earrings", "Kendra Scott", "Earrings", "image/Kendrascottdrop.jpg"));

    newProducts.add(new Product(null, 1, (float) 90.00, "Silver Ring", "Sliver ring", "Kendra Scott", "Ring", "image/Kendrascottring.jpg"));

    newProducts.add(new Product(null, 1, (float) 120.00, "Silver Earrings with lattice print","Lattice Studs",  "Kendra Scott", "Earrings", "image/Kendrascottstud.jpg" ));

    newProducts.add(new Product(null, 1, (float) 100.00, "Gold Drop Earrings","Drop Earrings",  "Madewell", "Earrings", "image/Madewelldrop.jpg" ));

    newProducts.add(new Product(null, 1, (float) 30.00, "Goldhoop earrings with leaf detail","Leaf Earrings",  "Madewell", "Earrings", "image/Madewellearings.jpg" ));

    newProducts.add(new Product(null, 1, (float) 50.00, "Mixed Ring Stack","Stack Rings",  "Madewell", "Rings", "image/Madewellstack.jpg" ));

    newProducts.add(new Product(null, 1, (float) 50.00, "Gold Bar Ring","Bar Ring",  "Madewell", "Rings", "image/Madewellthick.jpg" ));

    newProducts.add(new Product(null, 1, (float) 50.00, "gold chain necklace with coin pendent","Orbit Coin Pendant",  "Madewell", "Necklace", "image/Madewellcoin.jpg" ));

    newProducts.add(new Product(null, 1, (float) 45.00, "Long change necklace with triangle detail","Triangle Necklace",  "Madewell", "Necklace", "image/Madewelltriangle.jpg" ));

    newProducts.add(new Product(null, 1, (float) 50.00, "gold chain necklace with coin pendent","Orbit Coin Pendant",  "Madewell", "Necklace", "image/Madewellcoin.jpg" ));


    for (Product product : newProducts) {
        productService.save(product);
    }
       
    } else {
     System.out.println("Cart Full"); 
     for (Product product : productService.findAll()) {
         productService.deleteById(product.getId());
     }
}
    }
    
    @GetMapping("/")
    public String main() {
        addNew();
        return "main";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @ModelAttribute("brands")
    public List<String> brands() {
        return productService.findDistinctBrands();
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String brand,
                         Model model) {
        List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
        model.addAttribute("products", filtered); // Overrides the @ModelAttribute above.
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/admin")
    public String showAdmin() {
        return "admin/index";
    }
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    @PostMapping("/register")
    public String signUp(@ModelAttribute User user) {
        UserService userService = new UserService();
		userService.saveNew(user);
        return "redirect:/";
    }
}
