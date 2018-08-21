/**
 * 
 * @author zyh
 *
 */
public class MultiThreadReadByLine {
	public static void main(String[] args){
		
		FileReader fileReader = new FileReader("D:\\javaniotemp\\test1.txt",100,3);
		fileReader.registerHanlder(new FileLineDataHandler());
		fileReader.startRead();
	}
}
