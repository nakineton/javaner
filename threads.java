import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class threads extends Thread {
	ArrayList billecan=null;
	
	public threads(ArrayList billetecan)
	{
		this.billecan=billetecan;
	}
	public BufferedWriter ficheros() throws IOException
	{
		File fich = new File("/home/naki/Documents/cancelaciones.sql");
		FileWriter fichwri = new FileWriter(fich);
		BufferedWriter fichbu = new BufferedWriter(fichwri);
		return fichbu;
	}
	public void run()
	{

		BufferedWriter fichbu = null;
		try {
			fichbu = ficheros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator iteracan = this.billecan.iterator();
		while(iteracan.hasNext())
		{
			billete bcan = (billete)iteracan.next();
			try {
				fichbu.write("insert into cancelaciones values ('"+bcan.getId()+"','"+bcan.getDestino()+"','"+bcan.getCompañia()+"')");
				fichbu.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fichbu.flush();
			fichbu.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
