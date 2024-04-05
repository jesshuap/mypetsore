package com.orderitems.reserver.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.OffsetDateTime;

/**
 * Order
 */

public class Order implements Serializable {

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("email")
	private String email = null;

	@JsonProperty("products")
	private List<Product> products = null;

	@JsonProperty("shipDate")
	private OffsetDateTime shipDate = null;

	@JsonProperty("tags")
	private List<Tag> tags = null;

	public enum StatusEnum {
		PLACED("placed"),

		APPROVED("approved"),

		DELIVERED("delivered");

		private String value;

		StatusEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StatusEnum fromValue(String text) {
			for (StatusEnum b : StatusEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("status")
	private StatusEnum status = null;

	@JsonProperty("complete")
	private Boolean complete = null;

	public String getId() {
		return this.id;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Order order = (Order) o;
		return Objects.equals(this.id, order.id) && Objects.equals(this.products, order.products)
				&& Objects.equals(this.shipDate, order.shipDate) && Objects.equals(this.tags, order.tags)
				&& Objects.equals(this.status, order.status) && Objects.equals(this.complete, order.complete);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, products, shipDate, tags, status, complete);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Order {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    products: ").append(toIndentedString(products)).append("\n");
		sb.append("    shipDate: ").append(toIndentedString(shipDate)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    complete: ").append(toIndentedString(complete)).append("\n");
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
