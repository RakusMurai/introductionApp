package jp.co.rakus.introduction.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.rakus.introduction.domein.Customer;
import jp.co.rakus.introduction.domein.CustomerLogin;
import jp.co.rakus.introduction.repository.CustomerRepository;

/**
 * ログイン後、customerに権限情報を付与する.<br>
 * ログイン後に、検索されたcustomerに権限情報を付与<br>
 *
 * @author takumi.murai
 *
 */
@Service
@Transactional
public class CustomerDetailsServiceImpl implements UserDetailsService {

	/**
	 * 顧客情報に関するDBアクセスをDIする。
	 */
	@Autowired
	private CustomerRepository repository;

	/**
	 * 渡されたUserIdの顧客に認証情報を付与する.
	 *
	 * @param userId
	 *            入力された値。
	 * @return userIdの顧客に認証情報を付与して、CustomerLoginを返却。
	 * @throws UsernameNotFoundException
	 *             userIdが登録されなければエラーを返す。
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Customer customer = repository.findByUserId(userId);
		// 入力されたuserIdで顧客情報を取得する。
		if (customer == null) {
			throw new UsernameNotFoundException("そのIDは登録されていません。");
			// 顧客がなければエラーを返す。
		}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
		// すべての顧客にCUSTOMER権限を付与。
		// if (customer.isAdimin) {
		// authorities.addAll(new SimpleGrantedAuthority("ROLE_ADMIN"));
		// ※Admin権限を付与する場合に追加。
		// DBとcustomerDomeinにAdmin情報とisAdminメソッド(戻り値Boolean)を追加し、
		// DB上Admin情報を持っていたら、Admin権限を付与。
		return new CustomerLogin(customer, authorities);
	}
}
