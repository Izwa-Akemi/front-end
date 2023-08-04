package sql_injection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.Driver;

public class SQLNoInjection {

	public static void main(String[] args) {
		test1("Alice");
		test1("Dave");
		// SQL インジェクション
		test1("'; UPDATE account SET password = 'HACKED' --");
	}
	//SQLインジェクションを起こさないメソッド
	public static void test1(String username) {

		//1:ドライバの登録
		Driver driver = new Driver();
		//ファイルの設定パス
		String propertyPath = "src/sql_injection/jdbc.properties";

		//2:DB接続準備
		//設定ファイルの読み込みから行う
		Properties properties = new Properties();

		try {
			properties.load(new FileReader(propertyPath));
		}catch(IOException e) {
			System.out.println("ファイルが読み込み出来ませんでした");
			e.printStackTrace();
		}

		//DBのURLの設定
		String url = properties.getProperty("url");

		Properties info = new Properties();
		//ユーザー名の設定
		info.setProperty("user", properties.getProperty("user"));
		//パスワードの設定
		info.setProperty("password",properties.getProperty("password"));

		//実行したいSQL文の作成
		String sql = "SELECT password FROM account WHERE username = ?";

		//try-width-resourceを使ってSQLの実行
		try(
				//リンクの作成
				Connection con = driver.connect(url, info);
				//SQLインジェクションの回避の設定
				PreparedStatement smt = con.prepareStatement(sql);
				){
			//?に値を入れる（設定する）
			smt.setString(1, username);
			//結果の取得をする
			ResultSet rs = smt.executeQuery();
			if (rs.next()) {
				System.out.println("The password of " + username + " is '" + rs.getString("password") + "'.");
			} else {
				System.out.println("There is no user called '" + username + "'.");
			}
		}catch(SQLException e) {
			  System.err.println("Error happened when I try to access the database: " + properties.getProperty("url"));
	            e.printStackTrace();
		}



	}
}
