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
					boolean artitasexiste = false;
					for(int i=0;i<Main.Artistas.size();i++) 
					{
						if(cuadroacrear.Autor.equals(Main.Artistas.get(i).GetName())) 
						{
							Main.Artistas.get(i).AddCuadro(cuadroacrear);
							artitasexiste = true;
						}
				
					}
					if(artitasexiste)
					{
						
					}
					else 
					{
						Autores.CreateAutor(cuadroacrear.Autor);
					}
					
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
