package asi.cloud;

public class Usario 
{

	private String Nombre;
	private String Password;
	private int nº;
	private static int nextnum = 0;
	
	
	public Usario(String nombre,String contraseña,int num) 
	{
		Nombre = nombre;
		Password = contraseña;
		nº = num;
	}

	
	
	public static void CreateUser(String nombre, String pass) 
	{
		Usario useracrear = new Usario(nombre,pass,nextnum);
		Main.Usuarios.add(useracrear);
		nextnum++;
	}

	
	public String GetUser() 
	{
		return Nombre;
	}
	public String GetPass() 
	{
		return Password;
	}
	public int Getuserid() 
	{
		return nº;
	}
	
	
}
