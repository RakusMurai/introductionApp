package jp.co.rakus.introduction.domein;

import java.util.Date;

/**
 * メンバー情報.
 *
 * @author takumi.murai
 *
 */
public class Member {
	/**
	 * 自動採番
	 */
	private Integer id;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * 誕生日
	 */
	private Date birthday;
	/**
	 * 加入期
	 */
	private String phase;
	/**
	 * 出身地
	 */
	private String plase;
	/**
	 * 画像
	 */
	private String img;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getPlase() {
		return plase;
	}

	public void setPlase(String plase) {
		this.plase = plase;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}