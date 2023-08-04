package students;

public class StudentDto {
	//生徒ID
    private int id;
    //生徒の名前
    private String name;
    //生徒の年齢
    private int age;
    //生徒の科目
    private String subject;
    //性別
    private String gender;

    //コンストラクタの作成
	public StudentDto(int id, String name, int age, String subject, String gender) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.subject = subject;
		this.gender = gender;
	}
    //ゲッターとセッター
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", age=" + age + ", subject=" + subject + ", gender="
				+ gender + "]";
	}




}
