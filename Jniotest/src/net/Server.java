package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
	public static void main(String[] args){
		try {
			
			ServerSocketChannel serverchan = ServerSocketChannel.open();
			serverchan.bind(new InetSocketAddress("localhost",8787));
			serverchan.configureBlocking(false);
			Selector selector = Selector.open();
			serverchan.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public class SelectThread extends Thread{
		private Selector selector;
		public SelectThread(Selector selector){
			this.selector = selector;
		}
		public void run(){
			try{
				while(true){
					Set<SelectionKey> set = selector.selectedKeys();
					Iterator<SelectionKey> it = set.iterator();
					int ops = selector.select();
					while(it.hasNext()){
						SelectionKey key = it.next();
						if(key.isAcceptable()){
							ServerSocketChannel chan = (ServerSocketChannel)key.channel();
							SocketChannel client = chan.accept();
							
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
}
