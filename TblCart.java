package jp.co.internous.brown.model.domain;

import java.sql.Timestamp;

import jp.co.internous.brown.model.form.CartForm;

//idはユーザーID
public class TblCart {
		
	private int cartId;
	private int id;
	private int productId;
	private int productCount;
	private Timestamp createdAt;
	private Timestamp updatedAt;	
	
	public TblCart() {}
	
	public TblCart(CartForm form) {
		id = form.getId();
		productId = form.getProductId();
		productCount = form.getProductCount();
	}
	
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getId() {
		return id;
	}

	public void id(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}