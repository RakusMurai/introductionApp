package jp.co.rakus.introduction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.introduction.domein.Member;
import jp.co.rakus.introduction.repository.MemberRepository;

/**
 * メンバー情報を操作するサービスクラス.
 *
 * @author takumi.murai
 */
@Service
public class MemberService {

	/**
	 * 顧客情報のDBアクセス用クラスをオートワイヤード.
	 */
	@Autowired
	private MemberRepository memberRepository;

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

}
