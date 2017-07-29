import java.awt.Color;
import java.awt.Component;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class cambiotabla extends DefaultTableCellRenderer{
	private int columestado;
	public cambiotabla(int columna)
	{
		this.columestado=columna;
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		this.setBackground(Color.BLACK);
		this.setForeground(Color.green);
		if (arg0.getValueAt(arg4, arg5).equals("ca"))
		{
			this.setBackground(Color.red);//según criterios de billete cancelado o no 
			this.setForeground(Color.black);//distinto estilo para la tabla.
		}
		else if(arg0.getValueAt(arg4, arg5).equals("ok"))
		{
			this.setBackground(Color.green);
			this.setForeground(Color.black);
		}
		super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
		return this;
	}

}
