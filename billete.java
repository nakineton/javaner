
public class billete {
	private int id;
	private String nombre;
	private String apellidos;
	private String destino;
	private int horallegada;
	private int horallegadareal;
	private String compañia;
	private String estado;
	private int identificador;
	public billete(int id, String nombre, String apellidos, String destino, int horallegada, int horallegadareal, String compañia, String estado,int identificador)
	{
		this.id=id;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.destino=destino;
		this.horallegada=horallegada;
		this.horallegadareal=horallegadareal;
		this.compañia=compañia;
		this.estado=estado;
		this.identificador=identificador;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public String getDestino() {
		return destino;
	}
	public int getHorallegada() {
		return horallegada;
	}
	public int getHorallegadareal() {
		return horallegadareal;
	}
	public String getCompañia() {
		return compañia;
	}
	public String getEstado() {
		return estado;
	}
	public int getIdentificador()
	{
		return this.identificador;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public void setHorallegada(int horallegada) {
		this.horallegada = horallegada;
	}
	public void setHorallegadareal(int horallegadareal) {
		this.horallegadareal = horallegadareal;
	}
	public void setCompañia(String compañia) {
		this.compañia = compañia;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setIdentificador(int identificador)
	{
		this.identificador=identificador;
	}
}
