package jp.co.rakus.introduction.domein;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 顧客のログイン情報.
 *
 * @author takumi.murai
 *
 */
public class CustomerLogin extends User {

	/**
	 * <pre>
	 * 複数サーバー運用時に必要なID情報. 
	 * １プロジェクト複数サーバーの場合、Tomcatが自動的にアクセス集中を分散させる。
	 * ログイン→操作のような動作を行った場合、ログイン時はサーバー1、操作時はサーバー2にリクエストが送られることになる。
	 * その場合、サーバー1にはセッション情報が残っているが、サーバー2にはセッションがないので、再度ログインが必要となってしまう。
	 * serialVersionUIDを設定しておくと、UIDが同じプロジェクトを探し出し、サーバーをまたがってログインすることが可能になる。
	 * このUIDがサーバーごとに違う値を設定しておくと、エラーが発生する。
	 * このエラーを用いてバージョン管理を行える。（更新ごとにUIDを変更、古いUIDを利用しているとエラーが発生）
	 * UIDの設定がないと、このエラーが発生せず、更新前と更新後という別のプロジェクトが複数サーバーで運用されることになり、
	 * どういう動作が行われるのか不明のため、必ず設定が必要になる。
	 * </pre>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 顧客情報を受け取る
	 */
	private Customer customer;

	/**
	 * 取得した顧客情報に、権限情報を付与する,
	 *
	 * @param customer
	 *            ログインした顧客情報
	 * @param authorities
	 *            権限情報を持ったリスト
	 */
	public CustomerLogin(Customer customer, Collection<GrantedAuthority> authorities) {
		super(customer.getUserId(), customer.getPassword(), authorities);
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
