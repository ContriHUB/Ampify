import java.io.*;
interface File{
	void printFile();
}
class pdf_file implements File{
	public void printFile(){
		System.out.println("PDF");
	}
}
class text_file implements File{
	public void printFile(){
		System.out.println("TEXT File");
	}
}
class bin_file implements File{
	public void printFile(){
		System.out.println("Binary File");
	}
}

public class Main{
	private File fi;
	void setFile(File cp){
		fi = cp;
	}
	void printFiled(){
		fi.printFile();
	}
	public static void main(String args[])throws IOException{
		pdf_file a = new pdf_file();
		text_file b = new text_file();
		bin_file c = new bin_file();
		Main m = new Main();
		m.setFile(a);
		m.printFiled();
		m.setFile(b);
		m.printFiled();
		m.setFile(c);
		m.printFiled();
	}
}