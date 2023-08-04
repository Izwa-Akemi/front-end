package students;

public class Main {

	public static void main(String[] args) {
		StudentDao repository = new StudentDaoImpl();
		repository.save(new StudentDto(1,"Alice",19,"Java","Female"));
		repository.save(new StudentDto(2,"Bob",21,"Python","Male"));

		//一覧取得
		for(StudentDto student:repository.findAll()) {
			System.out.println(student);
		}
		//DBとの関係を断ち切る
		repository.close();

	}

}
