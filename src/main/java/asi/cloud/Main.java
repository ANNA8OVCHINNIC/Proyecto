package asi.cloud;

import static spark.Spark.*;


import java.util.*;

import spark.Request;
import spark.Response;
import spark.Route;

@SuppressWarnings("unused")
public class Main {
	
	static List<Usario> Usuarios;
	
	
	static List<Autores> Artistas;
	static List<Cuadros> ListaCuadros;
	
    static int Hora_apertura_laboral= 9;
    static int minutos_apertura_laboral=00;
    
    static int Hora_cierre_mediodia_laboral=13;
    static int minutos_cierre_mediodia_laboral =15;
    
    static int Hora_apertura_tarde_laboral=16;
    static int minutos_apertura_tarde_laboral=00;
    
    static int Hora_cierre_laboral=20;
    static int minutos_cierre_laboral=30;
    
    static int Hora_apertura_festivo=8;
    static int minutos_apertura_festivo=00;
    
    static int Hora_cierre_festivo=13;
    static int minutos_cierre_festivo=45;
    
	
	
	public static void main(String[] args) {
		Usuarios = new ArrayList<Usario>();
		
		Artistas = new ArrayList<Autores>();
		ListaCuadros = new ArrayList<Cuadros>();
		
		
		//Usarios mock creados para comprobar que funciona el servicio Login.
		Usario.CreateUser("Anna", "Pass1");
		Usario.CreateUser("Raul", "Pass2");
		Usario.CreateUser("Nadia", "Pass3");
		Usario.CreateUser("Jaime", "Pass4");
		
		//Prueba
		
		//Autores mock creados para comprobar que funcionan los servicios relacionados con el autor.
		Autores.CreateAutor("Picasso");
		Autores.CreateAutor("Monet");
		Autores.CreateAutor("Goya");
		Autores.CreateAutor("Kandinski");
		Autores.CreateAutor("Van Gogh");
		Autores.CreateAutor("Dali");
		
		//Cuadros mock creados para comprobar que funcionan los servicios relacionados con los cuadros.
		Cuadros.createCuadro("Composición VII", "Kandinski");
		Cuadros.createCuadro("Composición 8", "Kandinski");
		Cuadros.createCuadro("Composición X", "Kandinski");
		Cuadros.createCuadro("Guernica", "Picasso");
		Cuadros.createCuadro("La mujer que llora", "Picasso");
		Cuadros.createCuadro("Autorretrato", "Picasso");
		Cuadros.createCuadro("Mujer con sombrilla", "Monet");
		Cuadros.createCuadro("Crepusculo de Venecia", "Monet");
		Cuadros.createCuadro("Amapolas", "Monet");
		Cuadros.createCuadro("La maja desnuda", "Goya");
		Cuadros.createCuadro("Saturno devorando a sus hijos", "Goya");
		Cuadros.createCuadro("El aquelarre", "Goya");
		Cuadros.createCuadro("Autorretrato", "Van Gogh");
		Cuadros.createCuadro("Noche estrellada", "Van Gogh");
		Cuadros.createCuadro("Lirios", "Van Gogh");
		Cuadros.createCuadro("La persistencia de la memoria", "Dali");
		Cuadros.createCuadro("Joven virgen autosodomizada por los cuernos de su propia castidad", "Dali");
		
		
		//Crear server para probar los servicios.
		String port = System.getenv("PORT");
		
		if (port == null || port.isEmpty()) {
			port = "8080";
		}

		port(Integer.parseInt(port));

		get("/", (req, res) -> "Bienvenido al museo" + "\n" + "Introduzca sus datos por favor");
		post("/login",(req,res) -> loginservice(req,res));
		
		
		get("/autores", (req,res)-> listaautores(req,res));
		post("/autores/add",(req,res) -> anadeautor(req,res));
		post("/autores/delete", (req,res) -> borrautor(req,res));
		
		
		get("/cuadros",(req,res)-> listacuadros(req,res));
		post("/cuadros/add",(req,res) -> anadecuadro(req,res));
		post("/cuadros/delete", (req,res) -> borracuadro(req,res));
	
		
		get("/horario",(req,res)-> "El horario del museo es el siguietne:"+ "\n"+"De L-V: " + Hora_apertura_laboral+":"+minutos_apertura_laboral+ " a " +  Hora_cierre_mediodia_laboral+":"+
				minutos_cierre_mediodia_laboral+" y "+Hora_apertura_tarde_laboral+":"+minutos_apertura_tarde_laboral+" a "+Hora_cierre_laboral+":"+minutos_cierre_laboral+"\n"
				+ "Festivos: "+Hora_apertura_festivo+":"+minutos_apertura_festivo+" a "+Hora_cierre_festivo+":"+minutos_cierre_festivo);			
		post("horario/cambiar",(req,res)-> cambiarhorario(req,res));
		
		
		
		
	}
	public static String cambiarhorario(Request req, Response res) 
	{
		int hal=Integer.parseInt(req.queryParams("hal"));
		int mal=Integer.parseInt(req.queryParams("mal"));
		int hcml=Integer.parseInt(req.queryParams("hcml"));
		int mcml=Integer.parseInt(req.queryParams("mcml"));
		int hatl=Integer.parseInt(req.queryParams("hatl"));
		int matl=Integer.parseInt(req.queryParams("matl"));
		int hcl=Integer.parseInt(req.queryParams("hcl"));
		int mcl=Integer.parseInt(req.queryParams("mcl"));
		int haf=Integer.parseInt(req.queryParams("haf"));
		int maf=Integer.parseInt(req.queryParams("maf"));
		int hcf=Integer.parseInt(req.queryParams("hcf"));
		int mcf=Integer.parseInt(req.queryParams("mcf"));
		
		Hora_apertura_laboral= hal;
	    minutos_apertura_laboral=mal;
	    
	    Hora_cierre_mediodia_laboral=hcml;
	    minutos_cierre_mediodia_laboral =mcml;
	    
	    Hora_apertura_tarde_laboral=hatl;
	    minutos_apertura_tarde_laboral=matl;
	    
	    Hora_cierre_laboral=hcl;
	    minutos_cierre_laboral=mcl;
	    
	    Hora_apertura_festivo=haf;
	    minutos_apertura_festivo=maf;
	    
	    Hora_cierre_festivo=hcf;
	   	minutos_cierre_festivo=mcf;
	   	
	   	return "El horario del museo es el siguietne:"+ "\n"+"De L-V: " + Hora_apertura_laboral+":"+minutos_apertura_laboral+ " a " +  Hora_cierre_mediodia_laboral+":"+
				minutos_cierre_mediodia_laboral+" y "+Hora_apertura_tarde_laboral+":"+minutos_apertura_tarde_laboral+" a "+Hora_cierre_laboral+":"+minutos_cierre_laboral+"\n"
				+ "Festivos: "+Hora_apertura_festivo+":"+minutos_apertura_festivo+" a "+Hora_cierre_festivo+":"+minutos_cierre_festivo;
	}
	public static String loginservice(Request req, Response res) 
	{
		
		String usuario = req.queryParams("username");
		String password = req.queryParams("contrasena");
		boolean correcto = false;
		int index = -1;

		
		for (int i=0;i<Usuarios.size();i++)
		{
			if (usuario.equalsIgnoreCase(Usuarios.get(i).GetUser())) 
			{
				if(password.equals(Usuarios.get(i).GetPass())) 
				{
					correcto = true;
					index = Usuarios.get(i).Getuserid();
					
				}
				
			}
			
			
		}
		if (correcto) 
		{
			res.status(200);
			return Integer.toString(index);
		}
		else 
		{
			res.status(404);
			return "Usuario o contraseña incorrectos";
		}
		
	}
	

	public static String listaautores(Request req, Response res) {
	
		String lista_de_autores="";
		
		
		for(int i=0;i<Artistas.size();i++) 
		{
			
			lista_de_autores = lista_de_autores+Artistas.get(i).GetName()+"\n";
		}
		
		res.status(200);
		return lista_de_autores;
	}
	
	
	public static String listacuadros(Request req, Response res) 
	{
			String lista_de_cuadros="";
		
		
		for(int i=0;i<ListaCuadros.size();i++) {
			
			lista_de_cuadros = lista_de_cuadros+ListaCuadros.get(i).Get_Titlte()+" "+ ListaCuadros.get(i).Get_Author()+" " + ListaCuadros.get(i).Get_index()+"\n";
		}
		
		res.status(200);
		return lista_de_cuadros;
	}
	public static String anadecuadro(Request req, Response res) 
	{
		String cuadro = req.queryParams("cuadro");
		String autor = req.queryParams("autor");
		int index = -1;
		if(cuadro == null || autor == null) 
		{
			res.status(404);
			return "Información incorrecta";
		}
		else 
		{
			Cuadros.createCuadro(cuadro, autor);
			res.status(200);
			for (int i=0;i<ListaCuadros.size();i++) 
			{
				if(cuadro.equals(ListaCuadros.get(i).Get_Titlte()) && autor.equals(ListaCuadros.get(i).Get_Author())) 
				{
					index = ListaCuadros.get(i).Get_index();
				}
			}
			return "El índice del nuevo cuadro es "+ Integer.toString(index);
		}
			
		/*Comprovacion para ver si el cuadro a sido añadido
		String lista_de_cuadros="";
		
		
		for(int i=0;i<ListaCuadros.size();i++) {
			
			lista_de_cuadros = lista_de_cuadros+ListaCuadros.get(i).Get_Titlte()+" "+ ListaCuadros.get(i).Get_Author()+"\n";
			
		}
		
		
		return lista_de_cuadros;*/
		
		
		
	}
	public static String anadeautor(Request req, Response res) 
	{
		
		String autor = req.queryParams("autor");
		int index = -1;
		if(autor==null)
		{
			res.status(404);
			return "Información incorrecta";
		}
		else
		{
		Autores.CreateAutor(autor);
		res.status(200);
			for (int i=0;i<Artistas.size();i++) 
			{
				if(autor.equals(Artistas.get(i).GetName())) 
				{
					index = Artistas.get(i).Get_Index();
				}
			}
		return "El índice del nuevo artista es "+Integer.toString(index);
		}
		/*Comprovacion para ver si el autor a sido añadido
		String lista_de_autores="";
		
		
		for(int i=0;i<Artistas.size();i++) 
		{
			
			lista_de_autores = lista_de_autores+Artistas.get(i).GetName()+"\n";
		}
		
		res.status(200);
		return lista_de_autores;
		}*/
	}
	public static String borracuadro(Request req, Response res) 
	{
		String cuadroaborrar = req.queryParams("cuadro a borrar");
		String autor = req.queryParams("autor");
		String lista_de_cuadros="";
		
		boolean existecuadro = false;
		Cuadros indexaborrar = null;
		
		if(cuadroaborrar==null) 
		{
			res.status(404);
			return "Información incorrecta";
		}
		else if(autor == null) 
		{
			res.status(404);
			return "Falta información del autor";
		}
		else 
		{
			
			for (int i=0;i < ListaCuadros.size();i++) 
			{
				if(cuadroaborrar.equals(ListaCuadros.get(i).Get_Titlte()) && autor.equals(ListaCuadros.get(i).Get_Author())) 
				{
					existecuadro = true;
					indexaborrar = ListaCuadros.get(i);
					break;
				}
			}
			if(existecuadro) 
			{
				Cuadros.deleteCuadro(indexaborrar);
							
				
				//Comprovacion para ver si el cuadro a sido borrado
				for(int i=0;i<ListaCuadros.size();i++) 
				{
					
					lista_de_cuadros = lista_de_cuadros+ListaCuadros.get(i).Get_Titlte()+" "+ ListaCuadros.get(i).Get_Author()+"\n";
				}
				
				res.status(200);
				return lista_de_cuadros;
			}
			else
			{
				res.status (404);
				return "El cuadro indicado no existe en la base de datos";
			}
		}
		
		}
	public static String borrautor(Request req, Response res) 
	{
		String autor = req.queryParams("autor");
		String lista_de_cuadros ="";
		List<Cuadros> deletedcuadros;
		deletedcuadros = new ArrayList<Cuadros>();
		boolean artistaexiste = false;
		if(autor == null) 
		{
			res.status(404);
			return "Información incorrecta";
		}
		else 
		{
		for(int j=0;j<Artistas.size();j++)
		{
			
				if(Artistas.get(j).GetName().equals(autor))
				{
					artistaexiste = true;
					Artistas.remove(Artistas.get(j));
						
					for (int i=0;i<Main.ListaCuadros.size();i++) 
					{
						if(Main.ListaCuadros.get(i).Get_Author().equals(autor)) 
						{
							
							deletedcuadros.add(ListaCuadros.get(i));
							
						}	
							
					}
				}
				for(int z=0;z<deletedcuadros.size();z++) 
				{
					
					Cuadros.deleteCuadro(deletedcuadros.get(z));
				}
				
			
			

			}
		if(artistaexiste) 
		{
			//Comprobacion para ver si el autor y sus cuadors an sido borrado
			
			for(int i=0;i<ListaCuadros.size();i++) 
			{
						
				lista_de_cuadros = lista_de_cuadros+ListaCuadros.get(i).Get_Titlte()+" "+ ListaCuadros.get(i).Get_Author()+"\n";
			}
					
			res.status(200);
			return lista_de_cuadros;
		}
		else 
		{
			res.status(404);
			return "El autor no está en la base de datos";
		}
		}
	}
	
	
	

}
