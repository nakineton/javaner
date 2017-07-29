import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conectar {
	String usuario=null;
	String password=null;
	String basedatos=null;
	Connection conn = null;
	public Conectar() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		conesion();
	}
	public void conesion() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		usuario = "examen";
		password = "examen";
		basedatos="jdbc:mysql://192.168.1.11/sqlbbdd";
		conn= DriverManager.getConnection(basedatos,usuario,password);
	}
}
