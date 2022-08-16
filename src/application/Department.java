package application;

public class Department implements Comparable<Department> {
	private String depName;
	private HashTable<Student> studentRecord = new HashTable<>();
	private String stdFile;
//	private int numofS = 0;

	public Department(String depName, String stdFile) {
		this.depName = depName;
		this.stdFile = stdFile;
	}

	public String getStdFile() {
		return stdFile;
	}

	public void setStdFile(String stdFile) {
		this.stdFile = stdFile;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getdepName() {
		return depName;
	}

	@Override
	public boolean equals(Object obj) {
		Department o = (Department) obj;
		return this.depName.equals(o.depName);
	}

	@Override
	public int compareTo(Department o) {
		return this.depName.compareTo(o.depName);
	}

	public void addStudent(Student s) {
		studentRecord.insert(s);
	}

	public Student findStudent(String sname) {
		return studentRecord.find(sname).getData();
	}

	public Student deleteStudent(String sname) {
		return studentRecord.delete(sname).getData();
	}

	public String getStudents() {
		return "Entrolled Students \n" + studentRecord.toString();
	}

	public int numberOfStudent() {
		return studentRecord.size();
	}

	public int capacityOfDep() {
		return studentRecord.capacity();
	}

	public String getEnrolledStudents() {
		return studentRecord.getFullSpots();
	}

	@Override
	public String toString() {
		String s = "";
		return "[Department Name: " + depName + " , Student Folder: " + stdFile + "]" + s + "******\n";
	}
}
