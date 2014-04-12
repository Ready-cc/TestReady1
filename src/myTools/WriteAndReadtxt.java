package myTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/****************
 * 文件读取与保存
 * 
 * @author Administrator
 * 
 ****************/
public class WriteAndReadtxt {
	   public static BufferedReader bufread;
	    //指定文件路径和名称
	    private static String path = "D:/temporderid.txt";
	    private static  File filename = new File(path);
	    private static String readStr ="";
//	逐行写数据
	public void writeFile(String str, String savePath){
		FileWriter rw = null;
		try {
			rw = new FileWriter(savePath,true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(rw);
		try {
			bw.write("\n");
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("保存文件失败（路径错误）");;
		}
	}
//	只写一行数据
	public void writeline(String str, String savePath){
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(savePath));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("保存文件失败（路径错误）");;
		}
	}


	public void readFileByLines(String fileName,int line) {
		File file = new File(fileName);
		BufferedReader reader = null;
	
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line1 = 0;
			
			// 读取单行
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
					if(line1==line){
						tempString=reader.readLine();
						System.out.println(tempString);
			}
					line1++;
					
			}
			reader.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
//		return toString();

	}

	public String readTxtFile() {
		String read;
		FileReader fileread;
		try {
			fileread = new FileReader(filename);
			bufread = new BufferedReader(fileread);
			try {
				while ((read = bufread.readLine()) != null) {
					readStr = readStr + read + "\r\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readStr;
	}
	public static void main(String[] args) {
/*		WriteAndReadtxt fop = new WriteAndReadtxt();
		fop.writeFile("100-151515","d:/orderid.txt");
		fop.writeline("100-151515","d:/temporderid.txt");
		String aa = fop.readTxtFile();
		System.out.println(aa);*/

		
	}

}