import java.io.*;
import java.util.*;
import java.sql.*;

import javax.swing.JOptionPane;
public class fichero {
	ArrayList fichecan;
	Connection con;
	PreparedStatement stt;
	billete bcan;
	public fichero(ArrayList fichecan,Connection con) throws SQLException, IOException
	{
		this.fichecan=fichecan;
		this.con=con;
		this.insertarcan();
	}
	public void insertarcan() throws SQLException, IOException
	{
		Iterator iteracan= this.fichecan.iterator();
		
		while (iteracan.hasNext())
		{
			bcan= (billete)iteracan.next();
			stt = this.con.prepareStatement("insert into cancelaciones values (?,?,?)");
			stt.setInt(1, bcan.getId());
			stt.setString(2, bcan.getDestino());
			stt.setString(3, bcan.getCompañia());
			stt.executeUpdate();
		}
		int res=JOptionPane.showConfirmDialog(null, "Quires generar archivo SQL");
		if (res==0)
		{
			threads hilo = new threads(fichecan);//Para general el archivo sql usando la lcase extendia threads.
			hilo.run();
		}
	}
}
