package jp.co.rakus.introduction.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.introduction.domein.Customer;

/**
 * 顧客情報に関するDBアクセス.
 *
 * @author takumi.murai
 *
 */
@Repository
public class CustomerRepository {

	/**
	 * SQLを実行するクラスのオートワイヤード.
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * DB結果を受け取るRowMapper.<br>
	 * 結果をCustomerとして返却。
	 */
	private RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Customer customer = new Customer();
		customer.setEmail(rs.getString("email"));
		customer.setId(rs.getInt("id"));
		customer.setName(rs.getString("name"));
		customer.setPassword(rs.getString("password"));
		customer.setUserId(rs.getString("userId"));
		return customer;
	};

	/**
	 * ユーザーIDで登録済み顧客を探し出す.
	 *
	 * @return ユーザーIDの情報をもってCustomerにて返却。
	 */
	public Customer findByUserId(String userId) {
		String sql = "select id,userid,password,name,email from customers where userid = :userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		Customer customer = template.queryForObject(sql, param, customerRowMapper);
		return customer;
	}

}
