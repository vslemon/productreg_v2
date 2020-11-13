package net.ddns.lemoncello.productmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product",uniqueConstraints= {@UniqueConstraint(columnNames= {"barcode"})})


public class Product {
	
	@Id
	@Column(name = "barcode", unique=true)
	protected int barcode;

	@Column(name = "name")
	protected String name;

	@Column(name = "color")
	protected String color;

	@Column(name = "description")
	protected String description;

	public Product() {
		super();
	}

	public Product(int barcode, String name, String color, String description) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.color = color;
		this.description = description;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
