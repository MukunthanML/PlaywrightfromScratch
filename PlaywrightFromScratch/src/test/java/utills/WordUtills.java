package utills;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

//https://mkyong.com/java/java-read-and-write-microsoft-word-with-apache-poi/

public class WordUtills {

	

	public static void addTable() throws Exception {

		try (XWPFDocument doc = new XWPFDocument()) {

			XWPFTable table = doc.createTable();

			// Creating first Row
			XWPFTableRow row1 = table.getRow(0);
			row1.getCell(0).setText("First Row, First Column");
			row1.addNewTableCell().setText("First Row, Second Column");
			row1.addNewTableCell().setText("First Row, Third Column");

			// Creating second Row
			XWPFTableRow row2 = table.createRow();
			row2.getCell(0).setText("Second Row, First Column");
			row2.getCell(1).setText("Second Row, Second Column");
			row2.getCell(2).setText("Second Row, Third Column");

			// create third row
			XWPFTableRow row3 = table.createRow();
			row3.getCell(0).setText("Third Row, First Column");
			row3.getCell(1).setText("Third Row, Second Column");
			row3.getCell(2).setText("Third Row, Third Column");

			// save to .docx file
			try (FileOutputStream out = new FileOutputStream("table.docx")) {
				doc.write(out);
			}

		}

	}

	
	public static void addImage(String pathOfWordDocx,String pathOfImg) throws IOException, InvalidFormatException {

		
		try (XWPFDocument doc = new XWPFDocument()) {

			XWPFParagraph p = doc.createParagraph();
			XWPFRun r = p.createRun();
			r.setText(pathOfImg);
			r.addBreak();

			// add png image
			try (FileInputStream is = new FileInputStream(pathOfImg)) {
				r.addPicture(is, Document.PICTURE_TYPE_PNG, // png file
						pathOfImg, Units.toEMU(400), Units.toEMU(200)); // 400x200 pixels
			}

			try (FileOutputStream out = new FileOutputStream(pathOfWordDocx)) {
				doc.write(out);
			}
		}

	}
	public static void addImage() throws IOException, InvalidFormatException {

		String imgFile = "javalogo.png";

		try (XWPFDocument doc = new XWPFDocument()) {

			XWPFParagraph p = doc.createParagraph();
			XWPFRun r = p.createRun();
			r.setText(imgFile);
			r.addBreak();

			// add png image
			try (FileInputStream is = new FileInputStream(imgFile)) {
				r.addPicture(is, Document.PICTURE_TYPE_PNG, // png file
						imgFile, Units.toEMU(400), Units.toEMU(200)); // 400x200 pixels
			}

			try (FileOutputStream out = new FileOutputStream("images.docx")) {
				doc.write(out);
			}
		}

	}

	public static void addPargraph() throws IOException {

		String fileName = "hello.docx";

		try (XWPFDocument doc = new XWPFDocument()) {

			// create a paragraph
			XWPFParagraph p1 = doc.createParagraph();
			p1.setAlignment(ParagraphAlignment.CENTER);

			// set font
			XWPFRun r1 = p1.createRun();
			r1.setBold(true);
			r1.setItalic(true);
			r1.setFontSize(22);
			r1.setFontFamily("New Roman");
			r1.setText("I am first paragraph.");

			// save it to .docx file
			try (FileOutputStream out = new FileOutputStream(fileName)) {
				doc.write(out);
			}

		}

	}

}
