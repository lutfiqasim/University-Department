package application;
//3- students path files are in the same path as the departments files so make it read from there done--------- but check it for readStudents method

//(New entered departments)
//If entered id for new student is not from 7 digits then reject it
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	static Avl<Department> departments = new Avl<>();
	final static Font font3 = Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20);
	static String filesMainPath = "";
	final static Font font4 = Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 14);

	@Override
	public void start(Stage primaryStage) {
		primaryStage.initStyle(StageStyle.UTILITY);
		mainScreen(primaryStage);
	}

	private void mainScreen(Stage primaryStage) {
		VBox mainBox = new VBox(10);
		StackPane rootPane = new StackPane();
		// Buttons
		Button readData = new Button("Read Data");
		readData.setFont(font3);
		readData.setMaxSize(150, 150);
		Button departmentsButton = new Button("Departments");
		departmentsButton.setFont(font3);
		departmentsButton.setMaxSize(150, 150);
		Button studentButton = new Button("Students");
		studentButton.setFont(font3);
		studentButton.setMaxSize(150, 150);
		ImageView mainIm = new ImageView("courses.jpg");
		BorderPane imagePane = new BorderPane(mainIm);
		BorderPane.setAlignment(imagePane, Pos.CENTER);
		mainBox.getChildren().addAll(readData, departmentsButton, studentButton);
		mainBox.setAlignment(Pos.CENTER_LEFT);
		mainBox.setPadding(new Insets(50, 50, 50, 50));
		mainBox.setSpacing(50);
		rootPane.getChildren().addAll(mainBox, imagePane);
		BorderPane mainBane = new BorderPane();
		mainBane.getChildren().add(rootPane);
		mainBane.setLeft(mainBox);
		mainBane.setCenter(imagePane);
		BackgroundFill background_fill = new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		mainBane.setBackground(background);
		Scene mainScene = new Scene(mainBane, 950, 650);
		primaryStage.setTitle("Birzeit University");
		primaryStage.setScene(mainScene);
		primaryStage.setResizable(false);
		primaryStage.show();
		readData.setOnAction(e -> {
			primaryStage.close();
			primaryStage.hide();
			readDepartments(primaryStage);
		});
		departmentsButton.setOnAction(e -> {
			primaryStage.close();
			primaryStage.hide();
			departmentsScreen(primaryStage);
		});
		studentButton.setOnAction(e -> {
			primaryStage.close();
			primaryStage.hide();
			studentScreen(primaryStage);
		});
	}

	private void departmentsScreen(Stage primaryStage) {
		VBox mainBox = new VBox(50);
		Button printDep = new Button("Print Departments");
		printDep.setFont(font3);
		printDep.setMaxSize(400, 400);
		Button searchDep = new Button("Search Department");
		searchDep.setFont(font3);
		searchDep.setMaxSize(400, 400);
		Button addDep = new Button("Add Department");
		addDep.setFont(font3);
		addDep.setMaxSize(400, 400);
		Button delDep = new Button("Delete a Department");
		delDep.setFont(font3);
		delDep.setMaxSize(400, 400);
		Button findHeightofTree = new Button("Find height of tree");
		findHeightofTree.setFont(font3);
		findHeightofTree.setMaxSize(400, 400);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(400, 400);
		mainBox.getChildren().addAll(printDep, searchDep, addDep, delDep, findHeightofTree, back);
		mainBox.setPadding(new Insets(50, 50, 50, 50));
		mainBox.setAlignment(Pos.CENTER);
		BackgroundFill background_fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		mainBox.setBackground(background);
		Scene depChoice = new Scene(mainBox, 650, 650);
		primaryStage.setTitle("Departments of University");
		primaryStage.setScene(depChoice);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			mainScreen(primaryStage);
		});
		printDep.setOnAction(e -> {
			primaryStage.hide();
			printDepScreen(primaryStage);
		});
		searchDep.setOnAction(e -> {
			primaryStage.hide();
			searchDepScreen(primaryStage);
		});
		addDep.setOnAction(e -> {
			primaryStage.hide();
			insertDepScreen(primaryStage);
		});
		delDep.setOnAction(e -> {
			primaryStage.hide();
			deleteDepScreen(primaryStage);
		});
		findHeightofTree.setOnAction(e -> {
			int height = departments.getHeight();
			confirmation_Message("Height of current Avl tree is = " + height);
		});

	}

	private void printDepScreen(Stage primaryStage) {
		GridPane ReadDateP = new GridPane();
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		ReadDateP.setBackground(background);
		ReadDateP.setAlignment(Pos.CENTER);
		ReadDateP.setPadding(new Insets(10, 11.5, 12.5, 13));
		ReadDateP.setHgap(20);
		ReadDateP.setVgap(40);
		TextArea dataHold = new TextArea();
		dataHold.setFont(font3);
		dataHold.setEditable(false);
		Button getData = new Button("Print Departments");
		getData.setFont(font3);
		getData.setMaxSize(200, 200);
		Button backButton = new Button("Back");
		backButton.setFont(font3);
		backButton.setMaxSize(200, 200);
		ReadDateP.add(getData, 0, 0);
		ReadDateP.add(dataHold, 0, 1);
		ReadDateP.add(backButton, 0, 2);
		Scene readScene = new Scene(ReadDateP, 950, 650);
		primaryStage.setScene(readScene);
		primaryStage.show();
		backButton.setOnAction(e -> {
			primaryStage.hide();
			departmentsScreen(primaryStage);
		});
		getData.setOnAction(e -> {
			if (departments.isEmpty()) {
				warning_Message("No Departments are found");
			} else {
				dataHold.appendText(departments.inOrderTrarverse());
			}
		});
	}

	private void searchDepScreen(Stage primaryStage) {
		VBox dataEnter = new VBox(20);
		Label enterDepL = new Label("Enter Department name:");
		enterDepL.setFont(font3);
		enterDepL.setMaxSize(200, 200);
		TextField depNameT = new TextField();
		depNameT.setFont(font3);
		depNameT.setMaxSize(200, 200);
		Button getDep = new Button("Find");
		getDep.setFont(font3);
		getDep.setMaxSize(200, 200);
		dataEnter.getChildren().addAll(enterDepL, depNameT, getDep);
		Button backBtn = new Button("Back");
		backBtn.setFont(font3);
		backBtn.setMaxSize(200, 200);
		GridPane root = new GridPane();
		TextArea depaData = new TextArea();
		depaData.setFont(font3);
		depaData.setEditable(false);
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		root.setBackground(background);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10, 11.5, 12.5, 13));
		root.setVgap(20);
		root.setHgap(10);
		root.add(dataEnter, 0, 0);
		root.add(depaData, 0, 1);
		root.add(backBtn, 0, 2);
		Scene searchScreen = new Scene(root, 850, 650);
		primaryStage.setScene(searchScreen);
		primaryStage.setTitle("Find a Department");
		primaryStage.show();
		backBtn.setOnAction(e -> {
			primaryStage.hide();
			departmentsScreen(primaryStage);
		});
		getDep.setOnAction(e -> {
			if (depNameT.getText().isBlank()) {
				warning_Message("Fill in department Name first");
			} else {
				try {
					String departmentName = depNameT.getText().strip();
					TNode<Department> found = departments.find(new Department(departmentName, null));
					if (found != null) {
						depaData.appendText(found.toString());
						depaData.appendText(found.getData().getStudents());
					} else {
						warning_Message("Department Not found");
					}
				} catch (IllegalArgumentException ex) {
					warning_Message("Enter a Valid name");
				}
			}
		});
	}

	private void insertDepScreen(Stage primaryStage) {
		HBox depNameBox = new HBox(20);
		Label depNameL = new Label("Enter Department Name");
		depNameL.setFont(font3);
		depNameL.setMaxSize(200, 200);
		TextField depNameT = new TextField();
		depNameT.setFont(font3);
		depNameT.setMaxSize(600, 400);
		depNameBox.getChildren().addAll(depNameL, depNameT);
		HBox depFileBox = new HBox(20);
		Label depFileL = new Label("Student file Path\\\\Name");
		depFileL.setFont(font3);
		depFileL.setMaxSize(200, 200);
		TextField depFileT = new TextField();
		depFileT.setFont(font3);
		depFileT.setMaxSize(600, 400);
		depFileBox.getChildren().addAll(depFileL, depFileT);
		VBox dataBox = new VBox(20);
		dataBox.setAlignment(Pos.CENTER);
		dataBox.getChildren().addAll(depNameBox, depFileBox);
		Button add = new Button("Add");
		add.setFont(font3);
		add.setMaxSize(200, 200);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		HBox buttonBox = new HBox(20);
		buttonBox.getChildren().addAll(add, back);
		GridPane root = new GridPane();
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		root.setBackground(background);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10, 11.5, 12.5, 13));
		root.setHgap(10);
		root.setVgap(20);
		root.add(dataBox, 0, 0);
		root.add(buttonBox, 0, 1);
		Scene insertDepSceen = new Scene(root, 850, 650);
		primaryStage.setTitle("Add new Department");
		primaryStage.setScene(insertDepSceen);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			departmentsScreen(primaryStage);

		});
		add.setOnAction(e -> {
			if (depNameT.getText().isBlank()) {
				warning_Message("Enter Department Name First");
			} else {
				try {
					String depName = depNameT.getText().strip();
					String depFile = null;
					if (!depFileT.getText().strip().isBlank()) {
						depFile = depFileT.getText();
					}
					Department newDep = new Department(depName, depFile);
					boolean added = departments.insert(newDep);
					if (added) {
						confirmation_Message("Department " + depName + " was added successfully");
						readStudents(newDep);
					} else {
						warning_Message("Department already exists");
					}
				} catch (Exception ss) {
					warning_Message("Check entered values");
				}
			}
		});
	}

	private void deleteDepScreen(Stage primaryStage) {
		HBox depNameBox = new HBox(20);
		Label depNameL = new Label("Enter Department Name");
		depNameL.setFont(font3);
		depNameL.setMaxSize(200, 200);
		TextField depNameT = new TextField();
		depNameT.setFont(font3);
		depNameT.setMaxSize(600, 400);
		depNameBox.getChildren().addAll(depNameL, depNameT);
		Button delete = new Button("delete");
		delete.setFont(font3);
		delete.setMaxSize(200, 200);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		HBox buttonBox = new HBox(20);
		buttonBox.getChildren().addAll(delete, back);
		GridPane root = new GridPane();
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		root.setBackground(background);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10, 11.5, 12.5, 13));
		root.setHgap(10);
		root.setVgap(20);
		root.add(depNameBox, 0, 0);
		root.add(buttonBox, 0, 1);
		Scene insertDepSceen = new Scene(root, 850, 650);
		primaryStage.setTitle("Add new Department");
		primaryStage.setScene(insertDepSceen);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			departmentsScreen(primaryStage);

		});
		delete.setOnAction(e -> {
			if (depNameT.getText().isBlank()) {
				warning_Message("Enter Department Name First");
			} else {
				try {
					String depName = depNameT.getText().strip();
					String depFile = "";
					Department newDep = new Department(depName, depFile);
					TNode<Department> todel = departments.delete(newDep);
					if (todel == null) {
						warning_Message("Department Not found");
					} else {
						confirmation_Message("Department " + depName + " was Removed successfully");
					}
				} catch (NullPointerException ss) {
					warning_Message("Deleted Successfully");
				}
			}
		});
	}

	private void readDepartments(Stage newStage) {
		GridPane ReadDateP = new GridPane();
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		ReadDateP.setBackground(background);
		ReadDateP.setAlignment(Pos.CENTER);
		ReadDateP.setPadding(new Insets(10, 11.5, 12.5, 13));
		ReadDateP.setHgap(10);
		ReadDateP.setVgap(20);
		Label ChoseDepFile = new Label("Choose file");
		ChoseDepFile.setFont(font3);
		ImageView ChooseDepIm = new ImageView(new Image("ChooseDep.jpg"));
		ChooseDepIm.setFitHeight(30);
		ChooseDepIm.setFitWidth(30);
		Button btnDep = new Button("Choose file", ChooseDepIm);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		btnDep.setContentDisplay(ContentDisplay.RIGHT);
		btnDep.setFont(font3);
		ReadDateP.add(ChoseDepFile, 0, 0);
		ReadDateP.add(btnDep, 1, 0);
		ReadDateP.add(back, 1, 1);
		Scene ReadDataScene = new Scene(ReadDateP, 450, 450);
		newStage.setScene(ReadDataScene);
		newStage.show();
		back.setOnAction(e -> {
			newStage.close();
			mainScreen(newStage);
		});
		btnDep.setOnAction(e -> {
			try {
				FileChooser fc1 = new FileChooser();
				fc1.getExtensionFilters().add(new ExtensionFilter("TextFile", "*txt"));
				File Deps = fc1.showOpenDialog(newStage);
				filesMainPath = Deps.getAbsolutePath().toString();
				filesMainPath = filesMainPath.substring(0, filesMainPath.lastIndexOf("\\")) + "\\";
				try (Scanner input = new Scanner(Deps)) {
					String depName = "";
					while (input.hasNext()) {
						String data = input.nextLine();
						String[] str = data.split("/");
						depName = str[0].strip();
						Department currDep = new Department(depName, str[1].strip());
						departments.insert(currDep);
						String stdpath = new String(filesMainPath + currDep.getStdFile());
						File std = new File(stdpath);
						try (Scanner input2 = new Scanner(std)) {
							while (input2.hasNext()) {
								String studentD = input2.nextLine();
								String[] str2 = studentD.split("/");
								String name = str2[0].strip();
								int id = Integer.parseInt(str2[1].strip());
								float gpa = Float.parseFloat(str2[2].strip());
								char gender = str2[3].strip().charAt(0);
								departments.find(currDep).getData().addStudent(new Student(name, id, gpa, gender));
							}
						} catch (FileNotFoundException e1) {
							warning_Message("Error:No file was found for departmen 1");
//							departments = new Avl<>();
						}
					}
				} catch (FileNotFoundException ex) {
					warning_Message("Error:No file was found for department");
				}
			} catch (NullPointerException nullE) {
				warning_Message("No File Was Choosen");
			}
		});
	}

	private void readStudents(Department dep) {
		if (dep.getStdFile() != null) {
			File stdFile = new File(dep.getStdFile());
			try (Scanner input = new Scanner(stdFile)) {
				while (input.hasNext()) {
					String stdData = input.nextLine();
					String[] str = stdData.split("/");
					String name = str[0].strip();
					int id = Integer.parseInt(str[1].strip());
					float gpa = Float.parseFloat(str[2].strip());
					char gender = str[3].strip().charAt(0);
					dep.addStudent(new Student(name, id, gpa, gender));
				}
			} catch (FileNotFoundException e) {
				warning_Message("File wasn't found");
			}
		} else {
			warning_Message("No student Record file was entered");
		}
	}

	private void studentScreen(Stage primaryStage) {
		VBox mainBox = new VBox(20);
		Button printRecord = new Button("Print Student Record");
		printRecord.setFont(font3);
		printRecord.setMaxSize(400, 400);
		Button printHashFun = new Button("Get Hash Function");
		printHashFun.setFont(font3);
		printHashFun.setMaxSize(400, 400);
		Button insertRecord = new Button("Add a student");
		insertRecord.setFont(font3);
		insertRecord.setMaxSize(400, 400);
		Button searchRecord = new Button("Find a student");
		searchRecord.setFont(font3);
		searchRecord.setMaxSize(400, 400);
		Button deleteRecord = new Button("Remove a Student");
		deleteRecord.setFont(font3);
		deleteRecord.setMaxSize(400, 400);
		Button save = new Button("Save Student Records");
		save.setFont(font3);
		save.setMaxSize(400, 400);
		Button sizeBtn = new Button("Find Number of students");
		sizeBtn.setFont(font3);
		sizeBtn.setMaxSize(400, 400);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(400, 400);
		mainBox.getChildren().addAll(printRecord, printHashFun, insertRecord, searchRecord, deleteRecord, sizeBtn, save,
				back);
		mainBox.setPadding(new Insets(50, 50, 50, 50));
		mainBox.setAlignment(Pos.CENTER);
		BackgroundFill background_fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		mainBox.setBackground(background);
		Scene studentScene = new Scene(mainBox, 650, 650);
		primaryStage.setTitle("Students Record");
		primaryStage.setScene(studentScene);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			mainScreen(primaryStage);
		});
		printRecord.setOnAction(e -> {
			primaryStage.hide();
			printRecordScreen(primaryStage);
		});
		printHashFun.setOnAction(e -> {
			printHashFunScreen();
		});
		insertRecord.setOnAction(e -> {
			primaryStage.hide();
			insertRecordScreen(primaryStage);
		});
		searchRecord.setOnAction(e -> {
			primaryStage.hide();
			searchRecordScreen(primaryStage);
		});
		deleteRecord.setOnAction(e -> {
			primaryStage.hide();
			deleteRecordScreen(primaryStage);
		});
		sizeBtn.setOnAction(e -> {
			primaryStage.hide();
			sizeBtnScreen(primaryStage);
		});
		save.setOnAction(e -> {
			SaveRecordScreen(primaryStage);
		});
	}

	private void printRecordScreen(Stage primaryStage) {
		GridPane ReadDateP = new GridPane();
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill background_fill2 = new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		Background background2 = new Background(background_fill2);
		ReadDateP.setBackground(background);
		ReadDateP.setAlignment(Pos.CENTER);
		ReadDateP.setPadding(new Insets(10, 11.5, 12.5, 13));
		ReadDateP.setHgap(20);
		ReadDateP.setVgap(40);
		TextArea dataHold = new TextArea();
		dataHold.setFont(font3);
		dataHold.setEditable(false);
		Button getData = new Button("Print Student Records");
		getData.setFont(font3);
		getData.setMaxSize(300, 300);
		Button backButton = new Button("Back");
		backButton.setFont(font3);
		backButton.setMaxSize(300, 300);
		Label depN = new Label("Specify a department");
		depN.setFont(font3);
		depN.setMaxSize(300, 300);
		depN.setBackground(background2);
		depN.setStyle("-fx-border-color: black;");
		TextField depNT = new TextField();
		depNT.setFont(font3);
		depNT.setMaxSize(300, 300);
		depNT.setPromptText("Enter a department name to specify it");
		Label choseFlight = new Label("Flight Number");
		choseFlight.setFont(font3);
		HBox depNBox = new HBox(40);
		depNBox.getChildren().addAll(depN, depNT);
		ReadDateP.add(depNBox, 0, 0);
		ReadDateP.add(getData, 0, 1);
		ReadDateP.add(dataHold, 0, 2);
		ReadDateP.add(backButton, 0, 3);
		Scene readScene = new Scene(ReadDateP, 950, 650);
		primaryStage.setScene(readScene);
		primaryStage.show();
		backButton.setOnAction(e -> {
			primaryStage.hide();
			studentScreen(primaryStage);
		});
		getData.setOnAction(e -> {
			dataHold.clear();
			if (!depNT.getText().isBlank()) {
				String ss = "";
				String depName = depNT.getText().strip();
				TNode<Department> find = departments.find(new Department(depName, null));
				if (find != null) {
					ss += find.getData().getStudents();
					dataHold.appendText(ss);
				} else {
					warning_Message("Department wasn't found");
				}
			} else {
				warning_Message("Enter department Name");
			}
		});
	}

	private void printHashFunScreen() {
		String s = "Students Hash Function\n" + "	public int hashCode() {\r\n" + "		char ch[];\r\n"
				+ "		ch = studentName.toCharArray();\r\n" + "		int xlength = studentName.length();\r\n"
				+ "		int i, sum = 0;\r\n" + "		for (sum = 0, i = 0; i < xlength; i++) {\r\n"
				+ "			sum += ch[i];\r\n" + "		}\r\n" + "		return sum;\r\n" + "	}"
				+ "\n [Note: used moduls operator on hashCode to determine the \nindex of the current input]";
		confirmation_Message(s);

	}

	private void insertRecordScreen(Stage primaryStage) {
		Label depNL = new Label("Choose a Department");
		depNL.setFont(font3);
		depNL.setMaxSize(200, 200);
		TextField depNT = new TextField();
		depNT.setFont(font3);
		depNT.setMaxSize(200, 200);
		Label snameL = new Label("Enter Student name:");
		snameL.setFont(font3);
		snameL.setMaxSize(200, 200);
		TextField snameT = new TextField();
		snameT.setFont(font3);
		snameT.setMaxSize(200, 200);
		Label sIdL = new Label("Enter student Id");
		sIdL.setFont(font3);
		sIdL.setMaxSize(200, 200);
		TextField sIdT = new TextField();
		sIdT.setFont(font3);
		sIdT.setMaxSize(200, 200);
		Label sgpaL = new Label("Enter student gpa");
		sgpaL.setFont(font3);
		sgpaL.setMaxSize(200, 200);
		TextField sgpaT = new TextField();
		sgpaT.setFont(font3);
		sgpaT.setMaxSize(200, 200);
		RadioButton genderM = new RadioButton("M");
		RadioButton genderF = new RadioButton("F");
		genderF.setFont(font3);
		genderM.setFont(font3);
		ToggleGroup genderGroup = new ToggleGroup();
		genderM.setToggleGroup(genderGroup);
		genderF.setToggleGroup(genderGroup);
		GridPane ReadDateP = new GridPane();
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY);
		// create Background
		Background background = new Background(background_fill);
		ReadDateP.setBackground(background);
		ReadDateP.setAlignment(Pos.CENTER);
		ReadDateP.setPadding(new Insets(10, 11.5, 12.5, 13));
		ReadDateP.setHgap(20);
		ReadDateP.setVgap(40);
		ReadDateP.add(depNL, 0, 0);
		ReadDateP.add(depNT, 1, 0);
		ReadDateP.add(snameL, 0, 1);
		ReadDateP.add(snameT, 1, 1);
		ReadDateP.add(sIdL, 0, 2);
		ReadDateP.add(sIdT, 1, 2);
		ReadDateP.add(sgpaL, 0, 3);
		ReadDateP.add(sgpaT, 1, 3);
		ReadDateP.add(genderM, 0, 4);
		ReadDateP.add(genderF, 1, 4);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		Button add = new Button("ADD");
		add.setFont(font3);
		add.setMaxSize(200, 200);
		ReadDateP.add(add, 0, 5);
		ReadDateP.add(back, 1, 5);
		Scene readStudentSceen = new Scene(ReadDateP, 650, 650);
		primaryStage.setTitle("Add new Student");
		primaryStage.setScene(readStudentSceen);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			studentScreen(primaryStage);
		});
		add.setOnAction(e -> {
			if (depNT.getText().isBlank()) {
				warning_Message("Please choose a department");
			} else {
				if (snameT.getText().isBlank() || sIdT.getText().isBlank() || sgpaT.getText().isBlank()
						|| !(genderM.isSelected() || genderF.isSelected())) {
					warning_Message("All Fields are required");
				} else {
					try {
						String name = snameT.getText().strip();
						int id = Integer.parseInt(sIdT.getText().strip());
						float gpa = Float.parseFloat(sgpaT.getText().strip());
						char c = ' ';
						if (genderF.isSelected())
							c = 'F';
						else
							c = 'M';
						Student s = new Student(name, id, gpa, c);
						String dep = depNT.getText().strip();
						TNode<Department> chosendep = departments.find(new Department(dep, null));
						if (chosendep != null) {
							chosendep.getData().addStudent(s);
							confirmation_Message("Student " + s + " was added to department " + dep);
						} else
							warning_Message("Department " + dep + " was not found");
					} catch (IllegalArgumentException x) {
						warning_Message("Enter Valid data" + "Note" + "Name: String, id:Integer, gpa: Real");
					}
				}
			}
		});

	}

	private void searchRecordScreen(Stage primaryStage) {
		Label dep = new Label("Department:");
		dep.setFont(font3);
		dep.setMaxSize(200, 200);
		TextField depT = new TextField();
		depT.setFont(font3);
		depT.setMaxSize(200, 200);
		HBox depBox = new HBox(80);
		depBox.setAlignment(Pos.CENTER_LEFT);
		depBox.getChildren().addAll(dep, depT);
		Label stdL = new Label("Enter student Name");
		stdL.setFont(font3);
		stdL.setMaxSize(200, 200);
		TextField stdT = new TextField();
		stdT.setFont(font3);
		stdT.setMaxSize(200, 200);
		TextArea dataArea = new TextArea();
		dataArea.setMaxSize(700, 600);
		dataArea.setFont(font3);
		dataArea.setEditable(false);
		HBox enterD = new HBox(20);
		enterD.setAlignment(Pos.CENTER_LEFT);
		enterD.getChildren().addAll(stdL, stdT);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		Button find = new Button("Find");
		find.setFont(font3);
		find.setMaxSize(200, 200);
		HBox buttBox = new HBox(40);
		buttBox.getChildren().addAll(find, back);
		VBox main = new VBox(20);
		BackgroundFill background_fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		main.setPadding(new Insets(15, 15, 15, 15));
		main.setAlignment(Pos.CENTER);
		main.setBackground(background);
		main.getChildren().addAll(depBox, enterD, dataArea, buttBox);
		Scene searchScene = new Scene(main, 750, 650);
		primaryStage.setTitle("Find a student");
		primaryStage.setScene(searchScene);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			studentScreen(primaryStage);
		});
		find.setOnAction(e -> {
			dataArea.clear();
			if (depT.getText().isBlank())
				warning_Message("Enter Department of student");
			else {
				if (stdT.getText().isBlank()) {
					warning_Message("Enter student name to search for");
				} else {
					String depname = depT.getText().strip();
					String stdName = stdT.getText().strip();
					TNode<Department> chosenDep = departments.find(new Department(depname, null));
					try {
						if (chosenDep != null)
							dataArea.appendText(chosenDep.getData().findStudent(stdName).toString());
						else
							warning_Message("Department was not found");
					} catch (NullPointerException ne) {
						warning_Message("Student was not found");
					}
				}
			}
		});
	}

	private void deleteRecordScreen(Stage primaryStage) {
		Label dep = new Label("Department:");
		dep.setFont(font3);
		dep.setMaxSize(200, 200);
		TextField depT = new TextField();
		depT.setFont(font3);
		depT.setMaxSize(200, 200);
		HBox depBox = new HBox(80);
		depBox.setAlignment(Pos.CENTER_LEFT);
		depBox.getChildren().addAll(dep, depT);
		Label stdL = new Label("Enter student Name");
		stdL.setFont(font3);
		stdL.setMaxSize(200, 200);
		TextField stdT = new TextField();
		stdT.setFont(font3);
		stdT.setMaxSize(200, 200);
		TextArea dataArea = new TextArea();
		dataArea.setMaxSize(700, 600);
		dataArea.setFont(font3);
		dataArea.setEditable(false);
		HBox enterD = new HBox(20);
		enterD.setAlignment(Pos.CENTER_LEFT);
		enterD.getChildren().addAll(stdL, stdT);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		Button remove = new Button("Remove");
		remove.setFont(font3);
		remove.setMaxSize(200, 200);
		HBox buttBox = new HBox(40);
		buttBox.getChildren().addAll(remove, back);
		VBox main = new VBox(20);
		BackgroundFill background_fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		main.setPadding(new Insets(15, 15, 15, 15));
		main.setAlignment(Pos.CENTER);
		main.setBackground(background);
		main.getChildren().addAll(depBox, enterD, dataArea, buttBox);
		Scene searchScene = new Scene(main, 750, 650);
		primaryStage.setTitle("Find a student");
		primaryStage.setScene(searchScene);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			studentScreen(primaryStage);
		});
		remove.setOnAction(e -> {
			dataArea.clear();
			if (depT.getText().isBlank())
				warning_Message("Enter Department of student");
			else {
				if (stdT.getText().isBlank()) {
					warning_Message("Enter student name to remove from record");
				} else {
					String depname = depT.getText().strip();
					String stdName = stdT.getText().strip();
					TNode<Department> chosenDep = departments.find(new Department(depname, null));
					try {
						if (chosenDep != null) {
							Student toDel = chosenDep.getData().deleteStudent(stdName);
							dataArea.appendText(toDel.toString());
							// dataArea.appendText(chosenDep.getData().deleteStudent(stdName).toString());
						} else
							warning_Message("Department was not found");
					} catch (NullPointerException ne) {
						warning_Message("Student was not found");
					}
				}
			}
		});
	}

	private void sizeBtnScreen(Stage primaryStage) {
		Label dep = new Label("Department");
		dep.setFont(font3);
		dep.setMaxSize(200, 200);
		TextField depT = new TextField();
		depT.setFont(font3);
		depT.setMaxSize(200, 200);
		HBox labBox = new HBox(30);
		labBox.setAlignment(Pos.CENTER);
		labBox.getChildren().addAll(dep, depT);
		Button getSize = new Button("Find Size");
		getSize.setFont(font3);
		getSize.setMaxSize(200, 200);
		Button getCapacity = new Button("Get Current Capacity");
		getCapacity.setFont(font3);
		getCapacity.setMaxSize(200, 200);
		Button back = new Button("Back");
		back.setFont(font3);
		back.setMaxSize(200, 200);
		HBox btnBox = new HBox(30);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(getSize, getCapacity, back);
		VBox main = new VBox(30);
		main.setAlignment(Pos.CENTER);
		BackgroundFill background_fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
		main.setBackground(background);
		main.getChildren().addAll(labBox, btnBox);
		Scene sizeScene = new Scene(main, 650, 450);
		primaryStage.setTitle("Size and capacity of Departments");
		primaryStage.setScene(sizeScene);
		primaryStage.show();
		back.setOnAction(e -> {
			primaryStage.hide();
			studentScreen(primaryStage);
		});
		getSize.setOnAction(e -> {
			if (!depT.getText().isBlank()) {
				try {
					Department chosenDep = departments.find(new Department(depT.getText().strip(), null)).getData();
					confirmation_Message(
							"Size of department " + depT.getText() + " is = " + chosenDep.numberOfStudent());

				} catch (NullPointerException xx) {
					warning_Message("No such department");
				}
			} else
				warning_Message("Please fill department Name");
		});
		getCapacity.setOnAction(e -> {
			if (!depT.getText().isBlank()) {
				try {
					Department chosenDep = departments.find(new Department(depT.getText().strip(), null)).getData();
					confirmation_Message(
							"Current Capacity of department " + depT.getText() + " is = " + chosenDep.capacityOfDep());

				} catch (NullPointerException xx) {
					warning_Message("No such department");
				}
			} else {
				warning_Message("Please fill department Name");
			}
		});
	}

	private void SaveRecordScreen(Stage primaryStage) {
		try {
			LinkedList<Department> depList = departments.treeTolinkedList();
			while (depList != null) {
				String stdfiles = new String(filesMainPath + depList.getHead().getData().getStdFile());
				File recordsFile = new File(stdfiles);
				try (PrintWriter writer = new PrintWriter(recordsFile)) {
					writer.print(depList.getHead().getData().getEnrolledStudents());
					depList.deleteFirst();
				} catch (IOException ex1) {
					warning_Message("Error with saving data into file " + depList.getHead().getData().getStdFile());
				}
			}
//			warning_Message("No Departments were found");
		} catch (NullPointerException ex) {
			confirmation_Message("All Student Records were saved");
		}
	}

	public static void warning_Message(String x) {
		Alert alert = new Alert(AlertType.NONE);
		alert.setAlertType(AlertType.WARNING);
		alert.setContentText(x);
		alert.show();
	}

	public static void confirmation_Message(String x) {
		Alert alert = new Alert(AlertType.NONE);
		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setContentText(x);
		alert.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
