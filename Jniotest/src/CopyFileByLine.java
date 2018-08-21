import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileByLine {
	/**
	 * 按行读取文件
	 * @return
	 */
	public void readByLine(FileChannel inCh,ByteBuffer rbuf,FileChannel outCh,ByteBuffer wbuf){
		try {
			byte[] temp = new byte[0];//用来缓存上次读取剩下的部分
			int LF = "\n".getBytes()[0];//换行符
			while(inCh.read(rbuf) != -1){
				int position = rbuf.position();
				byte[] rbyte = new byte[position];
				rbuf.flip();
				rbuf.get(rbyte);
				//判断是否有换行符
				int startnum = 0;
				for(int i = 0; i < rbyte.length; i++){
					if(rbyte[i] == LF){//若存在换行符
						byte[] line = new byte[temp.length + i - startnum + 1];
						System.arraycopy(temp, 0, line, 0, temp.length);
						System.arraycopy(rbyte, startnum, line, temp.length, i - startnum + 1);
						startnum = i + 1;
						temp = new byte[0];
						writeByLine(outCh,wbuf,line);
					}
				}
				if(startnum < rbyte.length){//说明rbyte最后还剩不完整的一行
					byte[] temp2 = new byte[temp.length + rbyte.length - startnum];
					System.arraycopy(temp, 0, temp2, 0, temp.length);
					System.arraycopy(rbyte, startnum, temp2, temp.length, rbyte.length - startnum);
					temp = temp2;
				}
				rbuf.clear();
			}
			//兼容最后一行没有换行的情况
			if(temp.length > 0){
				writeByLine(outCh,wbuf,temp);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inCh.close();
				outCh.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 按行写入文件
	 * @param outCh
	 * @param wbuf
	 * @param data
	 * @param bufsize
	 * @return
	 */
	public void writeByLine(FileChannel outCh,ByteBuffer wbuf,byte[] line){
		try {
			wbuf = ByteBuffer.allocate(line.length);
			wbuf.clear();
			wbuf.put(line);
			wbuf.flip();
			String linstr = new String(line,0,line.length,"GBK");
			System.out.print(linstr.toString());
			while(wbuf.hasRemaining()){
				outCh.write(wbuf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}
