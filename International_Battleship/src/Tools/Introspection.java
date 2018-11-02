package tools;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Introspection {

	/**
	 * Invocation d'une méthode connaissant son nom sur un objet o
	 * en lui passant les bons paramétres
	 * 
	 * @param o - l'objet sur lequel agit la méthode
	 * @param args - la liste des paramétres de la méthode
	 * @param nomMethode - le nom de la méthode
	 * @return la méthode invoquée
	 * @throws Exception
	 */
	public static Object invoke(Object o, Object[] args, String nomMethode ) throws Exception	{
		Class<? extends Object>[] paramTypes = null;
		if(args != null){
			paramTypes = new Class<?>[args.length];
			for(int i=0;i<args.length;++i)	{
				paramTypes[i] = args[i].getClass();
			}
		}
		Method m = o.getClass().getMethod(nomMethode,paramTypes);
		return m.invoke(o,args);
	}
	

	/**
	 * création d'un objet connaissant le nom de la classe
	 * utilise un constructeur sans paramétre
	 * 
	 * @param className
	 * @return le nouvel objet crée
	 */
	public static Object newInstance(String className) {
		Object o = null;
		try	    {
			o = Class.forName (className).newInstance ();
		}
		catch (ClassNotFoundException e)	    {
			// La classe n'existe pas
			e.printStackTrace();
		}
		catch (InstantiationException e)	    {
			// La classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramétre
			e.printStackTrace();
		}
		catch (IllegalAccessException e)	    {
			// La classe n'est pas accessible
			e.printStackTrace();
		}
		return o;
	}


	/**
	 * construction à partir du nom de la classe et des paramétres du constructeur
	 * 
	 * @param className
	 * @param args - la liste des arguments du constructeur
	 * @return le nouvel objet crée
	 */
	public static Object newInstance(String className, Object[] args)	 {
		Object o = null;

		try {
			//On crée un objet Class
			Class<?> classe = Class.forName(className);
			// on récupére le constructeur qui a les paramétres args
			Class<?>[] paramTypes = null;
			if(args != null){
				paramTypes = new Class[args.length];
				for(int i=0;i<args.length;++i)	{
					paramTypes[i] = args[i].getClass();
				}
			}
			Constructor<?> ct = classe.getConstructor(paramTypes);
			// on instantie un nouvel objet avec ce constructeur et le bon paramétre
			o =  ct.newInstance (args);	
		}
		catch (ClassNotFoundException e)		{
			// La classe n'existe pas
			System.out.println("Classe inéxistante");
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)		{
			// La classe n'a pas le constructeur recherché
			System.out.println("Constructeur introuvable");
			e.printStackTrace();
		}
		catch (InstantiationException e)		{
			// La classe est abstract ou est une interface
			System.out.println("Classe Abstraite ou interface"); 
			e.printStackTrace();
		}
		catch (IllegalAccessException e)		{
			// La classe n'est pas accessible
			System.out.println("Classe inaccesible");
			e.printStackTrace();
		}
		catch (java.lang.reflect.InvocationTargetException e)		{
			// Exception déclenchée si le constructeur invoqué
			// a lui-méme déclenché une exception
			System.out.println("Constructeur corompu");
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)		{
			// Mauvais type de paramétre		
			System.out.println("Parametre type invalid");
			e.printStackTrace();
		}
		return o;
	}



}