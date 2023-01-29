package asi.cloud;

			public class Cuadros 
{
				private String Titulo;
				private String Autor;
				private int nº;
				private static int numcuadro=0;
				
				
				public Cuadros(String nombre, String autor,int indice) 
				{
					Titulo = nombre;
					Autor = autor;
					nº=indice;
	
				}
				public static void createCuadro(String tit,String aut)
				{
					Cuadros cuadroacrear = new Cuadros(tit,aut,numcuadro);
					Main.ListaCuadros.add(cuadroacrear);
					numcuadro++;
					
				}
				public String Get_Titlte() 
				{
					return Titulo;
				}	
					
				public String Get_Author() 
				{
					return Autor;
				}
				public int Get_index() 
				{
					return nº;
				}
				
				public static void deleteCuadro(Cuadros del) 
				{
					Main.ListaCuadros.remove(Main.ListaCuadros.indexOf(del));
				}
	
}
