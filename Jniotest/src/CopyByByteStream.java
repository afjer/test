import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * 复制文件
 * @author ywl
 *
 */
public class CopyByByteStream {

	public static void main(String[] args) {
		FileChannel infile = null;
		FileChannel outfile = null;
		try {
			RandomAccessFile inraf = new RandomAccessFile("D:\\javaniotemp\\test1.txt","r");
			RandomAccessFile outraf = new RandomAccessFile("D:\\javaniotemp\\test2.txt","rw");
			infile = inraf.getChannel();
			outfile = outraf.getChannel();
			ByteBuffer rbuf = ByteBuffer.allocate(10);
			while(infile.read(rbuf) != -1){
				rbuf.flip();
				outfile.write(rbuf, outfile.size());
				rbuf.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				infile.close();
				outfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
