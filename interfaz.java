import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.*;
import java.util.*;
public class interfaz extends JFrame implements WindowListener,ActionListener, MouseListener{
	JFrame ventana;
	JPanel billetes;
	JRadioButton elegir;
	JMenuItem guardarcancelaciones,salir,tablaprincipal;
	JMenu archivo;
	JMenuBar incluir;
	JTable tabla;
	DefaultTableModel tablemod,tablemod2;
	JScrollPane barra;
	Object[][] obj;
	String[] cabecera;
	JLabel tablal;
	Connection con;
	ArrayList <billete>bille;
	ArrayList <billete>listaca;
	JRadioButton barcelona;
	JRadioButton munich;
	JRadioButton lisboa;
	JRadioButton iberia;
	JRadioButton rayaner;
	ButtonGroup grupociudad;
	ButtonGroup grupocompañia;
	JButton buscar;
	String ciudad="";
	String compañia="";
	public interfaz(Connection conn, ArrayList billete,ArrayList listca) throws SQLException
	{
		this.con = conn;
		this.bille=billete;
		this.listaca=listca;
		this.conf();
		this.menus();
		this.tabla();
		this.radio();
		ventana.add(billetes);
		ventana.setVisible(true);
	}
	public void conf()
	{
		ventana = new JFrame("Billetes");
		billetes = new JPanel();
		billetes.setLayout(null);
		ventana.setSize(800, 400);
		ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.addWindowListener(this);
	}
	public void menus()
	{
		archivo =new JMenu("Principal");
		salir = new JMenuItem("Salir");
		guardarcancelaciones = new JMenuItem("Guardar cancelaciones");
		tablaprincipal = new JMenuItem("Tabla principal");
		incluir = new JMenuBar();
		incluir.add(archivo);
		archivo.add(tablaprincipal);
		archivo.add(guardarcancelaciones);
		archivo.add(salir);
		ventana.setJMenuBar(incluir);
		salir.addActionListener(this);
		guardarcancelaciones.addActionListener(this);
		tablaprincipal.addActionListener(this);
	}
	public void tabla() throws SQLException
	{
		barra = new JScrollPane();
		cabecera=new String[] {"Nombre","Apellidos","Destino", "hora llegada","hora llegada real","Compañia","Estado","identificador"};
		tablemod = new DefaultTableModel(obj, cabecera);
		tabla = new JTable(tablemod);
		barra.setViewportView(tabla);
		barra.setBounds(30, 30, 600, 200);
		billetes.add(barra);
		cambiotabla cam = new cambiotabla(7); //para generar el aspecto espefíco de la tabla.
		tabla.setDefaultRenderer(Object.class, cam);
		this.llenartabla();
		tabla.addMouseListener(this);
	}
	public void radio()
	{
		barcelona = new JRadioButton("Barcelona");
		munich = new JRadioButton("Munich");
		lisboa = new JRadioButton("Lisboa");
		iberia = new JRadioButton("Iberia");
		rayaner = new JRadioButton("Rayaner");
		buscar = new JButton("Buscar");
		barcelona.setBounds(30, 240, 100, 20);
		munich.setBounds(140, 240, 100, 20);
		lisboa.setBounds(250, 240, 100, 20);
		iberia.setBounds(30, 270, 100, 20);
		rayaner.setBounds(140, 270, 100, 20);
		buscar.setBounds(260, 270, 100, 30);
		grupociudad = new ButtonGroup();
		grupocompañia= new ButtonGroup();
		grupociudad.add(barcelona);
		grupociudad.add(munich);
		grupociudad.add(lisboa);
		grupocompañia.add(iberia);
		grupocompañia.add(rayaner);
		billetes.add(barcelona);
		billetes.add(munich);
		billetes.add(lisboa);
		billetes.add(iberia);
		billetes.add(rayaner);
		billetes.add(buscar);
		barcelona.addMouseListener(this);
		munich.addMouseListener(this);
		lisboa.addMouseListener(this);
		iberia.addMouseListener(this);
		rayaner.addMouseListener(this);
		buscar.addActionListener(this);
	}
	public void llenartabla() throws SQLException
	{
		borrartabla();
		Iterator iter = bille.iterator();
		while (iter.hasNext())//llenamos la tabla con los datos de la colleción
		{    //Generada en la clase datos.
			billete bi = (billete)iter.next();
			tablemod.addRow(new Object[]{bi.getNombre(),bi.getApellidos(),bi.getDestino(),bi.getHorallegada(),bi.getHorallegadareal(),bi.getCompañia(),bi.getEstado(),bi.getIdentificador()});
		}
	}
	public void borrartabla()
	{
		DefaultTableModel borrada = (DefaultTableModel) tabla.getModel();
		while (borrada.getRowCount()>0)
		{
			borrada.removeRow(0); //Para borrar la tabla, método usado a lo largo del programa.
		}
	}
	public void consultardatos() throws SQLException
	{
		if ((ciudad.contentEquals("")) || (compañia.contentEquals("")))
		{
			JOptionPane.showMessageDialog(null, "Tienes que introducir todos los datos de busqueda");
		}
		else
		{
			this.borrartabla();
			PreparedStatement stt= con.prepareStatement("select * from billetes  where destino=? and compañia= ?");
			stt.setString(1, ciudad);
			stt.setString(2, compañia);
			ResultSet resul = stt.executeQuery();
			while (resul.next()) //Para filtrar datos según criterios de los radiobutton.
			{
				tablemod.addRow(new Object[] {resul.getString(2),resul.getString(3),resul.getString(4),resul.getInt(5),resul.getInt(6),resul.getString(7),resul.getString(8),resul.getInt(9)});
			}
			cambiotabla cam1 = new cambiotabla(7);
			tabla.setDefaultRenderer(Object.class, cam1);
		}
	}
	static public void passwd(int tipo,Connection con,int iden,JFrame ventana,ArrayList bille)
	{
		if (tipo==1)
		{
			ventana.setVisible(false);//Con el click solicitamos info del usuario
			passwdclass pass= new passwdclass(con,iden,ventana,bille); //instanciamos la clase para solicitar el pssword establecido en l base de datos.
		}
		else if (tipo==0) //mediante este método estático controlamos en las distintas clases
		{        //Cuando se arán vivibles o la interfaz principal ||  la password || la de insercción de usuario.
			ventana.setVisible(true);
		}
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Statement stt= con.createStatement();
			stt.execute("truncate cancelaciones"); //la tabla cancelaciones solo tendrá datos durante la ejecución del programa.
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
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
		if (arg0.getSource()==salir)
		{
			int ressalir=JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir");
			if (ressalir==0)
			{
				Statement stt;
				try {
					stt = con.createStatement();
					stt.execute("truncate cancelaciones");
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}
		}
		if (arg0.getSource()==guardarcancelaciones)
		{
			try {
				fichero fich= new fichero(this.listaca,con); //esta la opción de
									//guardar las cancelaciones en modo sql de insert en un fichero.
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getSource()==tablaprincipal)
		{
			this.borrartabla(); //para restaurar los datos de todos los billetes en la interface.
			try {
				this.llenartabla();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (arg0.getSource()==buscar)
		{
			try {
				this.consultardatos(); //ejecutar la selección de los radiobutton.
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void mouseClicked(MouseEvent arg0)  {
		if (this.barcelona.isSelected())
		{
			ciudad=this.barcelona.getText();
		}
		if (this.munich.isSelected())
		{
			ciudad=this.munich.getText();
		}
		if (this.lisboa.isSelected())
		{
			ciudad=this.lisboa.getText();
		}
		if (this.iberia.isSelected())
		{
			compañia=this.iberia.getText();
		}
		if (this.rayaner.isSelected())
		{
			compañia=this.rayaner.getText();
		}
		if (arg0.getSource()==tabla)
		{
			DefaultTableModel tm = (DefaultTableModel)tabla.getModel();
			int row=tabla.getSelectedRow();
			String iden=String.valueOf(tm.getValueAt(row, 7));//lanzar la visualización clikando en el billete de la tabla
			int identificador =Integer.parseInt(iden);  //y para ello instacciamos la interface del password.
			interfaz.passwd(1, con,identificador,ventana,bille);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent arg0) {
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
