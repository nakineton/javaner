import java.sql.*;
import java.util.*;
public class datos {
	Connection con;
	ArrayList <billete>listabi = new ArrayList<billete>();//datos del billtes
	ArrayList <billete>listaca=new ArrayList<billete>();//datos de los vuelos cancelados.
	public datos(Connection conn)
	{
		this.con=conn;
	}
	public void datosbilletes() throws SQLException
	{
		Statement stt;
		stt=con.createStatement();
		
		ResultSet resul = stt.executeQuery("select * from billetes");
		while (resul.next())
		{
			billete b= new billete(resul.getInt(1),resul.getString(2),resul.getString(3),resul.getString(4),resul.getInt(5),resul.getInt(6),resul.getString(7),resul.getString(8),resul.getInt(9));
			listabi.add(b);
			if (b.getEstado().compareTo("ca")==0)
			{
				listaca.add(b);
			}
		}
		interfaz inter=new interfaz(con,listabi,listaca);//instanciamos la intefaz principal.
	}
}
//los campos de la tablas:
/* Identificador int --> pk. 
 * Nombre. varchar
 * Apellidos. varchar
 * Destino.varchar
 * hora de llegada prevista. int
 * hora de llegada real. int
 * Compañia con la que voló. varchar
 * Estado final del vuelo varchar (ok|ca) --> ca=cancelado.
 * id del usuario que voló. int
 */
