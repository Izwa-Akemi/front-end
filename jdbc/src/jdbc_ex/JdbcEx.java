package jdbc_ex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.postgresql.Driver;

public class JdbcEx {

	public static void main(String[] args) throws Exception {
		test1();
	}
	/*JDBCの使用手順
	 * １：Driverの登録
	 * ２：DBの接続準備
	 * 3:SQLの実行
	 * 4:接続を切る
	 * */

	public static void test1()throws Exception{
		//1ドライバーの登録
		Driver driver = new Driver();

		//2:DBの接続
		//リンクの作成
		String url = "jdbc:postgresql://localhost:5433/hello";
		//DBに接続するための設定情報　ユーザー名やパスワードの設定
		Properties info = new Properties();
		//ユーザー名の設置
		info.setProperty("user", "postgres");
		//パスワードの設定
		info.setProperty("password", "123456");
		//接続情報を作る
		Connection con = driver.connect(url, info);

		//3:SQLの実行
		Statement smt = con.createStatement();

		//SQL文の作成
		String sql = "SELECT * FROM student";

		//結果を取得
		ResultSet rs = smt.executeQuery(sql);

		//全てのデータのid,score,name
		while(rs.next()) {
			System.out.println("id:"+rs.getInt("id") +","+"name:"+rs.getString("name")+", score:"+rs.getInt("score"));
		}

		//4：リンクを切る
		smt.close();
		con.close();


	}

}
