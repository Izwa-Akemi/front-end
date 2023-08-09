package ecsite.ex.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class ProductEntity {
	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="productCategory")
	private String productCategory;
	
	@Column(name="productImage")
	private String productImage;
	
	@Column(name="productDescription")
	private String productDescription;

	public ProductEntity() {
	
	}

	public ProductEntity(String productName, String productCategory, String productImage, String productDescription) {
		this.productName = productName;
		this.productCategory = productCategory;
		this.productImage = productImage;
		this.productDescription = productDescription;
	}

	public ProductEntity(Long productId, String productName, String productCategory, String productImage,
			String productDescription) {
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productImage = productImage;
		this.productDescription = productDescription;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	
	
	
	
	
}
