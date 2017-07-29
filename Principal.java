import java.sql.SQLException;


public class Principal {
	public static void main (String agrs[] ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Conectar con = new Conectar();
		datos dat = new datos(con.conn); //calse main para establecer la conexión con bd.
		dat.datosbilletes(); //instaciamos la clase para cargar los datos en la colleción.
	}
}
