package asi.cloud;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;

@SuppressWarnings("unused")
public class Main {
	
	static List<Usario> Usuarios;
	
	
	static List<String> Autores;
	static List<Cuadros> ListaCuadros;
	
	public static void main(String[] args) {
		Usuarios = new ArrayList<Usario>();
		
		Autores = new ArrayList<String>();
		ListaCuadros = new ArrayList<Cuadros>();
		
		//Usarios mock creados para comprobar que funciona el servicio Login.
		Usario.CreateUser("Anna", "Pass1");
		Usario.CreateUser("Raul", "Pass2");
		Usario.CreateUser("Nadia", "Pass3");
		Usario.CreateUser("Jaime", "Pass4");
		
		
		
		//Autores mock creados para comprobar que funcionan los servicios relacionados con el autor.
		Autores.add("Picasso");
		Autores.add("Monet");
		Autores.add("Goya");
		Autores.add("Kandinski");
		Autores.add("Van Gogh");
		Autores.add("Dali");
		
		//Cuadros mock creados para comprobar que funcionan los servicios relacionados con los cuadros.
		Cuadros.createCuadro("Composición VII", "Kandinski");
		Cuadros.createCuadro("Composición 8", "Kandinski");
		Cuadros.createCuadro("Composición X", "Kandisnki");
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
		get("/autores", (req,res)-> listaautores(req,res));
		get("/cuadros",(req,res)-> listacuadros(req,res));
		get("/horario",(req,res)-> "El horario del museo es el siguietne:"+ "\n"+"De L-V: 9.00-14.00 y 17.00-21.30"+"\n"+"S:9.00-14.00"+"\n"+"D:Cerrado");			
		post("/login",(req,res) -> loginservice(req,res));
		post("/addcuadro",(req,res) -> anadecuadro(req,res));
		post("/addautor",(req,res) -> anadeautor(req,res));
		post("/deletecuadro", (req,res) -> borracuadro(req,res));
		post("/deleteautor", (req,res) -> borrautor(req,res));
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
		
		
		for(int i=0;i<Autores.size();i++) 
		{
			
			lista_de_autores = lista_de_autores+Autores.get(i)+"\n";
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
		
		if(cuadro == null || autor == null) 
		{
			res.status(404);
			return "Información incorrecta";
		}
		else 
		{
		Cuadros.createCuadro(cuadro, autor);
		res.status(200);
		
		//Comprovacion para ver si el cuadro a sido añadido
		String lista_de_cuadros="";
		
		
		for(int i=0;i<ListaCuadros.size();i++) {
			
			lista_de_cuadros = lista_de_cuadros+ListaCuadros.get(i).Get_Titlte()+" "+ ListaCuadros.get(i).Get_Author()+"\n";
			
		}
		
		res.status(200);
		return lista_de_cuadros;
		}
	}
	public static String anadeautor(Request req, Response res) 
	{
		
		String autor = req.queryParams("autor");
		if(autor==null)
		{
			res.status(404);
			return "Información incorrecta";
		}
		else
		{
		Autores.add(autor);
		res.status(200);
		
		//Comprovacion para ver si el autor a sido añadido
		String lista_de_autores="";
		
		
		for(int i=0;i<Autores.size();i++) 
		{
			
			lista_de_autores = lista_de_autores+Autores.get(i)+"\n";
		}
		
		res.status(200);
		return lista_de_autores;
		}
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
							
				
				////Comprovacion para ver si el cuadro a sido borrado
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
		
		if(autor == null) 
		{
			res.status(404);
			return "Información incorrecta";
		}
		else 
		{
			if(Main.Autores.indexOf(autor)!=-1) 
			{
			Main.Autores.remove(Main.Autores.indexOf(autor));
				for (int i=0;i<Main.ListaCuadros.size();i++) 
				{
					if(Main.ListaCuadros.get(i).Get_Author().equals(autor)) 
					{
						
						deletedcuadros.add(ListaCuadros.get(i));
						
						
						
					}
				}
				for(int j=0;j<deletedcuadros.size();j++) 
				{
					
					Cuadros.deleteCuadro(deletedcuadros.get(j));
				}
				
			
				res.status(200);
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
