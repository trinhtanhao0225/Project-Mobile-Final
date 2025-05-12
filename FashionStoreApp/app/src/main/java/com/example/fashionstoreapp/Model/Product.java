package com.example.fashionstoreapp.Model;

<<<<<<< HEAD
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;
=======
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
import java.util.List;

public class Product implements Serializable {
	@SerializedName("id")
<<<<<<< HEAD
	@Expose
	private int id;

	@SerializedName("product_Name")
	@Expose
	private String product_Name;

	@SerializedName("description")
	@Expose
	private String description;

	@SerializedName("sold")
	@Expose
	private int sold;

	@SerializedName("is_Active")
	@Expose
	private int is_Active;

	@SerializedName("is_Selling")
	@Expose
	private int is_Selling;

	@SerializedName("created_At")
	@Expose
	private Date created_At;

	@SerializedName("price")
	@Expose
	private int price;

	@SerializedName("quantity")
	@Expose
	private int quantity;

//	@SerializedName("category")
//	@Expose
	private Category category;

//	@SerializedName("productImage")
//	@Expose
	private List<ProductImage> productImage;

//	@SerializedName("order_Item")
//	@Expose
	private List<Order_Item> order_Item;

//	@SerializedName("order")
//	@Expose
	private List<Cart> cart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
	}

	public int getIs_Selling() {
		return is_Selling;
	}

	public void setIs_Selling(int is_Selling) {
		this.is_Selling = is_Selling;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductImage> getProductImage() {
		return productImage;
	}

	public void setProductImage(List<ProductImage> productImage) {
		this.productImage = productImage;
	}

	public List<Order_Item> getOrder_Item() {
		return order_Item;
	}

	public void setOrder_Item(List<Order_Item> order_Item) {
		this.order_Item = order_Item;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public Product(int id, String product_Name, String description, int sold, int is_Active, int is_Selling, Date created_At, int price, int quantity, Category category, List<ProductImage> productImage, List<Order_Item> order_Item, List<Cart> cart) {
		this.id = id;
		this.product_Name = product_Name;
		this.description = description;
		this.sold = sold;
		this.is_Active = is_Active;
		this.is_Selling = is_Selling;
		this.created_At = created_At;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.productImage = productImage;
		this.order_Item = order_Item;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", product_Name='" + product_Name + '\'' +
				", description='" + description + '\'' +
				", sold=" + sold +
				", is_Active=" + is_Active +
				", is_Selling=" + is_Selling +
				", created_At=" + created_At +
				", price=" + price +
				", quantity=" + quantity +
				", category=" + category +
				", productImage=" + productImage +
				", order_Item=" + order_Item +
				", cart=" + cart +
				'}';
	}
}
=======
	private int id;
	@SerializedName("product_name")
	private String productName;
	@SerializedName("description")
	private String description;
	@SerializedName("sold")
	private int sold;
	@SerializedName("is_active")
	private int isActive;
	@SerializedName("is_selling")
	private int isSelling;
	@SerializedName("created_at")
	private String createdAt;
	@SerializedName("price")
	private double price;
	@SerializedName("quantity")
	private int quantity;
	@SerializedName("product_images")
	private List<ProductImage> productImages;
	@SerializedName("category_id")
	private int categoryId;
	@SerializedName("order_item")
	private List<Object> orderItem; // Thêm để khớp với JSON
	@SerializedName("cart")
	private List<Object> cart; // Thêm để khớp với JSON
	@SerializedName("rating")
	private List<Object> rating; // Thêm để khớp với JSON

	// Constructor 11 tham số
	public Product(int id, String productName, String description, int sold, int isActive, int isSelling,
				   String createdAt, double price, int quantity, List<ProductImage> productImages, int categoryId) {
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.sold = sold;
		this.isActive = isActive;
		this.isSelling = isSelling;
		this.createdAt = createdAt;
		this.price = price;
		this.quantity = quantity;
		this.productImages = productImages;
		this.categoryId = categoryId;

	}

	// Constructor mặc định
	public Product() {}



	// Getters và setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getProductName() { return productName; }
	public void setProductName(String productName) { this.productName = productName; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public int getSold() { return sold; }
	public void setSold(int sold) { this.sold = sold; }
	public int getIsActive() { return isActive; }
	public void setIsActive(int isActive) { this.isActive = isActive; }
	public int getIsSelling() { return isSelling; }
	public void setIsSelling(int isSelling) { this.isSelling = isSelling; }
	public String getCreatedAt() { return createdAt; }
	public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
	public double getPrice() { return price; }
	public void setPrice(double price) { this.price = price; }
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public List<ProductImage> getProductImages() { return productImages; }
	public void setProductImages(List<ProductImage> productImages) { this.productImages = productImages; }
	public int getCategoryId() { return categoryId; }
	public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
	public List<Object> getOrderItem() { return orderItem; }
	public void setOrderItem(List<Object> orderItem) { this.orderItem = orderItem; }
	public List<Object> getCart() { return cart; }
	public void setCart(List<Object> cart) { this.cart = cart; }
}
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
