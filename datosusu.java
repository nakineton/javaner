import java.sql.*;
import javax.swing.*;
import java.util.*;

import javax.swing.JOptionPane;
public class datosusu {
	Connection conusu;
	PreparedStatement stt;
	ResultSet resul;
	int identificador;
	JFrame pass;
	String datos="";
	ArrayList <billete>bille;
	public datosusu(Connection con, int id,JFrame pass,ArrayList bille) throws SQLException, excepccion
	{
		this.conusu=con;
		this.identificador=id;
		this.bille=bille;
		this.pass=pass;
		this.estausu();
	}
	public void estausu() throws SQLException, excepccion
	{
		stt=conusu.prepareStatement("select * from usuarios");
		resul=stt.executeQuery();
		boolean insert=false;
		while(resul.next())
		{
			if (resul.getInt(1)==this.identificador)
			{
				datosusumen();
				insert=false;
				break;
			}
			else
			{
				insert=true;
			}
		}
		if (insert) //si existe el identificador del usuario en la tabla usuario  y en la tabla billetes
		{ //muestra datos de este
			throw new excepccion(this.conusu,pass,this.bille,this.identificador);
		}//si no lanza la excepcion. 
	}
	public void datosusumen() throws SQLException
	{
		
		stt= conusu.prepareStatement("select * from usuarios where iden=?");
		stt.setInt(1, this.identificador);
		resul=null;
		resul=stt.executeQuery();//Metodo para extraer los datos
		resul.next();
		datosconflictos(resul);
		JOptionPane.showMessageDialog(null, datos);
	}
	public void datosconflictos(ResultSet resul)//Método para acoplar los datos para visualizarlos.
	{
		try {
			datos="Usuario: " + resul.getString(2) + " con DNI: "+ resul.getInt(3) + " y edad: "+resul.getInt(4);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
/*tabla usuararios
 * iden int -->identificador concordante con la tabla bileltes.
 * nomyape varchar -->nombre y apelldios sacados de la tabla billetes.
 * dni int 
 * edad int
 * */
