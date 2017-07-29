import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
public class insertarusu implements WindowListener,ActionListener{
	JFrame usu,pass;
	JPanel usupanel;
	JLabel intro,DNI,edad;
	JMenuItem salir;
	JMenu archivo;
	JMenuBar incluir;
	JTextArea dni,ed;
	JButton aceptar;
	ArrayList <billete>bille;
	Connection con;
	int iden;
	public insertarusu(Connection conusu,JFrame pass,ArrayList bille,int iden)
	{
		this.pass=pass;
		this.bille=bille;
		con=conusu;
		this.iden=iden;
		conf();
		menus();
		insertar();
		usu.add(usupanel);
		usu.setVisible(true);
	}
	public void conf()
	{
		usu = new JFrame("Insertar");
		usupanel = new JPanel();
		usupanel.setLayout(null);
		usu.setSize(500, 200);
		usu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		usu.setLocationRelativeTo(null);
		usu.addWindowListener(this);
	}
	public void menus()
	{
		archivo =new JMenu("Principal");
		salir = new JMenuItem("Salir");
		incluir = new JMenuBar();
		incluir.add(archivo);
		archivo.add(salir);
		usu.setJMenuBar(incluir);
		salir.addActionListener(this);
	}
	public void insertar()
	{
		intro = new JLabel("Introduce datos");
		intro.setBounds(20, 40, 140, 30);
		DNI = new JLabel("DNI");
		DNI.setBounds(20, 90, 100, 30);
		edad = new JLabel("Edad");
		edad.setBounds(130, 90, 140, 30);
		dni = new JTextArea();
		dni.setBounds(20, 130, 100, 30);
		ed = new JTextArea();
		ed.setBounds(130, 130, 100, 30);
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(250, 40, 100, 160);
		usupanel.add(intro);
		usupanel.add(DNI);
		usupanel.add(edad);
		usupanel.add(dni);
		usupanel.add(ed);
		usupanel.add(aceptar);
		aceptar.addActionListener(this);
	}
	public void inserbasedatos() throws SQLException
	{
		String nombre="";
		Iterator iter= this.bille.iterator();
		while (iter.hasNext())
		{
			billete bbd=(billete)iter.next();//Insertar los datos en la tabla usuario
			if (bbd.getIdentificador()==this.iden)
			{
				nombre=bbd.getNombre() + " " +bbd.getApellidos();
				break;
			}
		}
		PreparedStatement stt=con.prepareStatement("Insert into usuarios values (?,?,?,?)");
		stt.setInt(1, this.iden);
		stt.setString(2, nombre);
		stt.setString(3, this.dni.getText());
		stt.setString(4, this.ed.getText());
		stt.executeUpdate();
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		usu.setVisible(false);
		interfaz.passwd(0,null,0,this.pass,null);
	}
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(aceptar))
		{
			try {
				this.inserbasedatos();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			usu.setVisible(false);
			interfaz.passwd(0,null,0,this.pass,null);
		}
	}
}
