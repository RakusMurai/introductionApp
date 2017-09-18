package jp.co.rakus.introduction.domein;

/**
 * 顧客情報.
 *
 * @author takumi.murai
 *
 */
public class Customer {
	/**
	 * 自動採番
	 */
	private Integer id;
	/**
	 * 登録ID
	 */
	private String userId;
	/**
	 * パスワード
	 */
	private String password;
	/**
	 * 顧客名
	 */
	private String name;
	/**
	 * 顧客連絡先
	 */
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
