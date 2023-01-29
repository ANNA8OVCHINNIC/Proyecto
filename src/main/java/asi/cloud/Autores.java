package asi.cloud;

import java.util.ArrayList;
import java.util.List;

public class Autores 
{
	private String Nombre;
	private List<Cuadros> obras;
	private int nº;
	private static int nextnum = 0;
	
	public  Autores(String nombre, int indice) 
	{
		Nombre = nombre;
		nº= indice;
		obras = new ArrayList<Cuadros>();
	}
	public static void CreateAutor(String nombre) 
	{
		Autores autoracrear = new Autores(nombre,nextnum);
		Main.Artistas.add(autoracrear);
		nextnum++;
	}
	public String GetName() 
	{
		return Nombre;
	}
	public void AddCuadro(Cuadros cuadro) 
	{
		this.obras.add(cuadro);
	}
	public int Get_Index() 
	{
		return nº;
	}
}
