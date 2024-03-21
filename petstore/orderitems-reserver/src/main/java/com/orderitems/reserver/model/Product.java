package com.orderitems.reserver.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Product
 */
@SuppressWarnings("serial")
public class Product implements Serializable {
	private Long id = null;

	private Integer quantity = null;

	private String name = null;

	private String photoURL;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Product product = (Product) o;
		return Objects.equals(this.id, product.id) && Objects.equals(this.quantity, product.quantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quantity);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Product {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    photoURL: ").append(toIndentedString(photoURL)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
