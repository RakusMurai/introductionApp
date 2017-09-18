package jp.co.rakus.introduction.domein;

/**
 * 商品情報.
 *
 * @author takumi.murai
 *
 */
public class Item {
	/**
	 * 自動採番
	 */
	private Integer id;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 金額
	 */
	private Integer price;
	/**
	 * 画像
	 */
	private String img;
	/**
	 * 商品紹介
	 */
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
