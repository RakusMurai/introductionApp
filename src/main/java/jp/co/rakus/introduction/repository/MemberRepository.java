package jp.co.rakus.introduction.repository;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.rakus.introduction.domein.Member;

@Repository
public class MemberRepository {
	/**
	 * SQLを実行するクラスのオートワイヤード.
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * DB結果を受け取るRowMapper.<br>
	 * 結果をMemberとして返却。
	 */
	private RowMapper<Member> memberRowMapper = (rs, i) -> {
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setImg(rs.getString("img"));
		member.setName(rs.getString("name"));
		member.setPhase(rs.getString("phase"));
		member.setPlase(rs.getString("plase"));
		member.setBirthday(dateformat(rs));
		return member;
	};

	/**
	 * DBから日付を取得し、Date型に返却.
	 */
	private Date dateformat(ResultSet rs) {
		try {
			String date = rs.getString("birthday");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = format.parse(date);
			return birthday;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * メンバーテーブルの全権検索.
	 *
	 * @return Member型のリストを返却。
	 */
	public List<Member> findAll() {
		String sql = "select id,name,birthday,phase,plase,img from members";
		return template.query(sql, memberRowMapper);

	}

}
