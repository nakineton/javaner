import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
public class passwdclass implements ActionListener,WindowListener{
	JFrame pass,vent;
	JPanel passpanel;
	JLabel passlabelu,passlabelp;
	JTextArea passpassu,passpassp;
	JButton passok;
	JMenuItem salir;
	JMenu archivo;
	JMenuBar incluir;
	Connection con;
	int identificador;
	ArrayList <billete>bille;
	public passwdclass(Connection conn, int iden,JFrame ventana,ArrayList bille)
	{
		this.vent=ventana;
		this.con=conn;
		this.identificador=iden;
		this.bille=bille;
		conf();
		menus();
		passzone();
		pass.add(passpanel);
		pass.setVisible(true);
	}
	public void conf()
	{
		pass = new JFrame("Password");
		passpanel = new JPanel();
		passpanel.setLayout(null);
		pass.setSize(500, 200);
		pass.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pass.setLocationRelativeTo(null);
		pass.addWindowListener(this);
	}
	public void menus()
	{
		archivo =new JMenu("Principal");
		salir = new JMenuItem("Salir");
		incluir = new JMenuBar();
		incluir.add(archivo);
		archivo.add(salir);
		pass.setJMenuBar(incluir);
		salir.addActionListener(this);
	}
	public void passzone()
	{
		passlabelu = new JLabel("Introduce usuario");
		passlabelu.setBounds(20, 20, 150, 30);
		passlabelp = new JLabel("Introduce constraseña");
		passlabelp.setBounds(20, 80, 170, 30);
		passpassu = new JTextArea();
		passpassu.setBounds(220, 20, 90, 30);
		passpassp = new JTextArea();
		passpassp.setBounds(220, 80, 90, 30);
		passok = new JButton();
		passok.setIcon(new ImageIcon("/home/naki/workspace/Billetes/Ok-icon.png"));
		passok.setBounds(330, 20, 200, 110);
		passpanel.add(passlabelu);
		passpanel.add(passpassu);
		passpanel.add(passlabelp);
		passpanel.add(passpassp);
		passpanel.add(passok);
		passok.addActionListener(this);
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource()==salir)
		{
			int ressalir=JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres salir");
			if (ressalir==0)
			{
				pass.setVisible(false);
				interfaz.passwd(0,null,0,this.vent,this.bille);
			}
		}
		if (arg0.getSource()==passok)
		{
			try {
				Statement stt = con.createStatement();
				ResultSet resul = stt.executeQuery("select * from constraseña");
				boolean ok=false;
				while (resul.next())
				{
					if((passpassu.getText().equals(resul.getString(1))) && (passpassp.getText().equals(resul.getString(2))))
					{
						pass.setVisible(false);
						interfaz.passwd(0,null,0,this.vent,null);
						try{ //si esta registrado el usuario en la tabla constraseña
						datosusu datos = new datosusu(con,identificador,vent,this.bille);
						} //instanciamos datosusu.
						catch(excepccion e)
						{
							
						}
						ok=true;
					}
				}
				if (ok==false)
				{
					passpassu.setText("");
					passpassp.setText("");
					JOptionPane.showMessageDialog(null, "Autentificación fallida");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(WindowEvent arg0) {
		pass.setVisible(false);
		interfaz.passwd(0,null,0,this.vent,null);
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
}
/*Tabl
 * user varchar
 * passwd varchar
*/