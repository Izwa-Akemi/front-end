package students;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.postgresql.Driver;

public class StudentDaoImpl implements StudentDao{
	//Driverの設定
	private static final Driver DRIVER = new Driver();
	//ファイルパスの設定
	private static final String PROPERTY_PATH = "src/student/dao.properties";

	private String url ="";
	private String user="";
	private String password="";
	private Connection con = null;

	//コンストラクタの作成をしてDB接続情報を記載
	public StudentDaoImpl() {
		//設定情報（ファイル）の取得
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(PROPERTY_PATH));
		}catch(IOException e) {
			System.out.println("ファイルが読み込めませんでした");
			e.printStackTrace();
		}

		url =properties.getProperty("url");
		user=properties.getProperty("user");
		password=properties.getProperty("password");

		Properties info = new Properties();
		info.setProperty("user",user);
		info.setProperty("password",password);

		try {
			con = DRIVER.connect(url, info);
		}catch(SQLException e) {
			System.out.println("DBのアクセスに失敗しました");
			e.printStackTrace();
		}

	}
	//saveメソッドの実装
	@Override
	public int save(StudentDto studentDto) {
		String sql ="INSERT INTO student VALUES (?,?,?,?,?)";
		int result = 0;
		try(PreparedStatement smt = con.prepareStatement(sql)){
			smt.setInt(1, studentDto.getId());
			smt.setString(2, studentDto.getName());
			smt.setInt(3, studentDto.getAge());
			smt.setString(4, studentDto.getSubject());
			smt.setString(5, studentDto.getGender());
			result = smt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("データを挿入出来ませんでした");
			e.printStackTrace();
		}
		return result;
	}
	//データ一覧の取得
	@Override
	public List<StudentDto> findAll(){
		List<StudentDto>result = new ArrayList<StudentDto>();
		String sql = "SELECT * FROM student";
		try(Statement smt = con.createStatement()){
			ResultSet rs = smt.executeQuery(sql);
			while(rs.next()) {
				result.add(new StudentDto(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getInt("age"),
						rs.getString("subject"),
						rs.getString("gender")
						));
			}
		}catch(SQLException e) {
			System.out.println("データを取得出来ませんでした");
			e.printStackTrace();
		}
		return result;
	}

	//DBから接続情報を切るメソッドの実装
	@Override
	public void close() {
		if(con != null) {
			try {
				con.close();
			}catch(SQLException e) {
				System.out.println("データベースを閉じることが出来ませんでした");
				e.printStackTrace();
			}
		}
	}

}
