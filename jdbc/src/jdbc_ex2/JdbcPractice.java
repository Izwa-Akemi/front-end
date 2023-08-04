package jdbc_ex2;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.postgresql.Driver;

public class JdbcPractice {

	public static void main(String[] args) {
		test2();

	}
	/*JDBCの使用手順
	 * １：Driverの登録
	 * ２：DBの接続準備
	 * 3:SQLの実行
	 * 4:接続を切る
	 * */
	public static void test2() {
		//1:ドライバの登録
		Driver driver = new Driver();
		//外部ファイルのパスの設定
		String propertyPath = "src/jdbc_ex2/hello.properties";

		//外部ファイルの内容を取得する
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(propertyPath));
		}catch(IOException e) {
			System.out.println("ファイルの読み込みに失敗しました");
			e.printStackTrace();
		}

		//URLの設定
		String url = properties.getProperty("url");
		Properties info = new Properties();
		//ユーザー名の設定
		info.setProperty("user", properties.getProperty("user"));
		//パスワードの設定
		info.setProperty("password", properties.getProperty("password"));

		//try-width-resource
         try(
        		 //接続するためのリンクの作成
        		 Connection con = driver.connect(url, info);
        		 //SQLの実行
        		 Statement smt = con.createStatement();
        	){
        	 //データの一覧を取得する
        	 String sql = "SELECT * FROM pokemon";
        	 //結果を取得
        	 ResultSet rs = smt.executeQuery(sql);

        	//全てのデータのid,score,name
     		while(rs.next()) {
     			System.out.println("id:"+rs.getInt("id") +","+"name:"+rs.getString("name")+", type_id:"+rs.getInt("type_id"));
     		}

         }catch(SQLException e) {
        	 System.out.println("データベースのアクセス失敗");
        	 e.printStackTrace();
         }

	}

}
