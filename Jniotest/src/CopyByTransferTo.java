import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class CopyByTransferTo {

	public static void main(String[] args) {
		FileChannel infile = null;
		FileChannel outfile = null;
		try {
			RandomAccessFile inraf = new RandomAccessFile("D:\\javaniotemp\\test1.txt","r");
			RandomAccessFile outraf = new RandomAccessFile("D:\\javaniotemp\\test2.txt","rw");
			infile = inraf.getChannel();
			outfile = outraf.getChannel();
			//将源文件通道中的数据传送到目的文件所在通道，从源文件下标为0的位置开始，读取最多infile.size()个字节数据到outfie通道中
			infile.transferTo(0, infile.size(), outfile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				infile.close();
				outfile.close();
			}catch(IOException e){
				e.printStackTrace();
			}

		}

	}

}
