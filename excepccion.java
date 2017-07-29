import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
public class excepccion extends Exception {
	Connection con;
	JFrame pass;
	ArrayList <billete>bille;
	int iden;
	public excepccion(Connection conusu,JFrame pass,ArrayList bille,int iden){
		con=conusu;
		this.bille=bille;
		this.pass=pass;
		this.iden=iden;
		pass.setVisible(false);
		insertarusu inser = new insertarusu(con,pass,this.bille,this.iden);
	}
}
