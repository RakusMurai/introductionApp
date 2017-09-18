package jp.co.rakus.introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * ログイン認証に関する設定.<br>
 * SpringSecurity導入時に必須のクラス。<br>
 * configureメソッドをオーバーライドすることで、ログイン認証にかかわる設定を行う。
 *
 * @author takumi.murai
 */
@Configuration
// このクラスがログイン設定用クラスであることを表す。
@EnableWebSecurity
// SpringSecurityのWebアプリ用の機能を追加。
// EnableWebMvcSecurityはSpringVersion4以降では非推奨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService customerDetailsService;

	/**
	 * ログイン認証してなくても読み込みを行うファイルの設定.<br>
	 * 引数をWebSecurityにてオーバーライド。<br>
	 * antMatchersに設定されたパスに含まれるファイルは、<br>
	 * ログイン認証していなくても読み込みを行うことができる。<br>
	 * Cssファイルなどは、ログイン前のページにも利用したいので、ここで除外する。
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/font/**");
	}

	/**
	 * 認可、ログイン、ログアウトに関する設定.<br>
	 * 引数HttpSecurityを引数にしてオーバーライド。<br>
	 * authorizeRequests()、formLogin()、logout()メソッドにて設定を行う。<br>
	 * http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();<br>
	 * のようにメソッドチェーンで各一行にて実装する。<br>
	 * ここでは理解のためにコメントにて改行している。<br>
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 認可に関する設定を行うメソッド。
				.antMatchers("/").permitAll()
				// antMatchersに設定したパスは、すべて認可不要。
				// 「.antMatchers("/admin/**").hasRole("ADMIN")」←今回は権限設定ないためコメントアウト
				// ADMIN権限でログインしている場合のみアクセス可(権限設定時の「ROLE_」を除いた文字列を指定)
				// 「.antMatchers("/member/**").hasRole("MEMBER")」←今回は権限設定ないためコメントアウト
				// MEMBER権限でログインしている場合のみアクセス可(権限設定時の「ROLE_」を除いた文字列を指定)
				.anyRequest().authenticated();
		// 上のantMachersに登場していないパスは、すべて認可必要。

		http.formLogin()
				// ログインに関する設定を行うメソッド。
				.loginPage("/login")
				// 初期ページ表示を行うメソッドのパス(ログイン認証されてない時もこのパスに移動される)
				.loginProcessingUrl("/loginprocess")
				// ログインボタンを押した際のJsp上のパス(このパスが呼ばれると、Springがログイン処理を行う)
				.failureUrl("/login?error=true")
				// ログイン失敗時に呼ばれるメソッドのパス。ここではName=error、Value=trueを付与して移動。
				.defaultSuccessUrl("/", false)
				// 第1引数:ログイン成功時に呼ばれるメソッドのパス。
				// 第2引数:
				// true :認証後常に第1引数のパスに移動
				// false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら移動前のパスを再表示
				.usernameParameter("userId")
				// 認証時に使用するユーザ名のリクエストパラメータ名
				.passwordParameter("password");
		// 認証時に使用するパスワードのリクエストパラメータ名

		http.logout()
				// ログアウトに関する設定を行うメソッド
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
				// ログアウト処理を行うメソッドへのパス
				.logoutSuccessUrl("/")
				// ログアウト後の行うメソッドへのパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID")
				// ログアウト後、Cookieに保存されているセッションIDを削除
				.invalidateHttpSession(true);
		// true:ログアウト後、セッションを無効にする
		// false:セッションを無効にしない

	}

	/**
	 * 認証に関する設定.<br>
	 * 入力されたログイン情報を照合するサービスクラス「UserDetailsService」の設定や<br>
	 * パスワードの暗号化に利用する「PasswordEncorder」の設定を行う。
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerDetailsService).passwordEncoder(new StandardPasswordEncoder());
	}

	/**
	 * <pre>
	 * 暗号化を行うオブジェクトの実装.
	 * ここでBean指定することで暗号化やマッチ確認するクラスで
	 * 		&#64;Autowired
	 * 		private PasswordEncoder passwordEncoder;
	 * と記載するとDIできるようになる。
	 * DIされるとDIされたクラスにこのオブジェクトが実装される。
	 * ここではSHA-256アルゴリズムでの実装を行う。
	 * </pre>
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}

}
