package jp.co.internous.brown.model.form;

import java.io.Serializable;

public class CartForm implements Serializable {
	private static final long serialVersionUID = 6156759298082343173L;
	
	private int id;
	private int productId;
	private int productCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		
}