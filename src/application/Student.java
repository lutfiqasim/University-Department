package application;

public class Student implements Comparable<Student> {
	private String studentName;
	private int id;
	private float gpa;
	private char gender;

	public Student() {

	}

	public Student(String studentName, int id, float gpa, char gender) {
		try {
			this.studentName = studentName;
			setId(id);
			this.gpa = gpa;
			this.gender = gender;
		} catch (Exception e) {
			Main.warning_Message("Wrong id formatting");
		}
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) throws Exception {
//		int length = String.valueOf(id).length();
//		if (length != 6) {
//			System.out.println("Unaccepted id number");
//			throw new Exception();
//		} else
//			this.id = id;
		this.id = id;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		// out with collisions in range from 3-5
		// less number of collisions
		char ch[];
		ch = studentName.toCharArray();
		int xlength = studentName.length();
		int i, sum = 0;
		for (sum = 0, i = 0; i < xlength; i++) {
			sum += ch[i];
		}
		return sum;
		// out with many collisions from range 4-6
//		int hash = 7;
//		for (int i = 0; i < studentName.length(); i++) {
//			hash = hash * 31 + studentName.charAt(i);
//			if (hash < 0)
//				hash = Math.abs(hash) * 31;
//		}
//		return Math.abs(hash);
	}

	@Override
	public boolean equals(Object obj) {
		String o = String.valueOf(obj);
		return this.studentName.strip().equals(o.strip());
	}

	@Override
	public int compareTo(Student o) {
		if (this.id > o.id)
			return 1;
		else if (this.id < o.id)
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return studentName + "/" + id + "/" + gpa + "/" + gender;
	}

}
