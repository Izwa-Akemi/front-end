package students;

import java.util.List;

public interface StudentDao {
	//データを全件取得するメソッド
	List<StudentDto>findAll();
	//データを挿入するメソッド
	int save(StudentDto studentDto);

	//DBからの接続を切るメソッド
	void close();
}
