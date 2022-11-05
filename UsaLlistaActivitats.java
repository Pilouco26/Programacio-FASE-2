package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import dades.*;
import java.util.concurrent.TimeUnit;
public class UsaLlistaActivitats {

   static String[] split;
   static String[] split2;
   static Scanner teclat = new Scanner(System.in);

   public static void main(String[] args) throws FileNotFoundException {
      int numLinies = demanarNroLinies();
      String[] dataset = llegirLiniesFitxer(numLinies);
      LlistaActivitats s1 = new LlistaActivitats(numLinies);

      // opcionament podem mostrar el contingut de les linies que hem llegit del fitxer
     
      int i = 0;

      for (i = 0; i < dataset.length; i++) {
         split = dataset[i].split(";");			//Separem categories
         String[] ini = split[1].split("/");
         int dia = Integer.valueOf(ini[0]); 	//Separem dies dels mesos dels anys
         int mes = Integer.valueOf(ini[1]);
         int any = Integer.valueOf(ini[2]);
         Data dini = new Data(dia, mes, any);	//Cridem el constructor de la data 

         String[] fini = split[0].split("/");	//Separem dies dels mesos dels anys
         int dia2 = Integer.valueOf(fini[0]);
         int mes2 = Integer.valueOf(fini[1]);
         int any2 = Integer.valueOf(fini[2]);
         Data fin = new Data(dia2, mes2, any2); //Cridem el constructor de la data 

         String titol = split[2];				//Obtenim el titol

         split2 = split[4].split("/");				//Separem les provincies de les comarquesm dels municipis
         String provincia = split2[0];
         String comarca = split2[1];
         String municipi = split2[2];
         ActivitatCultural a1 = new ActivitatCultural(dini, fin, titol, provincia, comarca, municipi);	//Construim l'activitat cultural
         String[] cat = split[3].split(",");
         
         for (int k = 0; k < cat.length; k++) {
            a1.afegirCategoria(cat[k]);					//Afegim tantes categories com tingui
         }
         s1.afegirActivitat(a1);	//Afegim activitat a la llista		

      }

      boolean sortir = false;

      while (!sortir) {				//Mentres no volguem acabar continuem el bucle

         System.out.println(" [1] Eliminar poblacio\n [2] Primera activitat segons comarca\n [3] Consultar activitat amb més dies en cartellera\n " +
            "[4] Consultar activitats segons provincia\n [5] Dades segons dates\n [6] Consultar dades de certa categoria\n [7] Quina provincia te mes activitats" +
            " programades\n [8] Conjunt d'activitats de la llista\n [9] Sortir :)");
         int menu = Integer.parseInt(teclat.nextLine());							//MENU

         switch (menu) {
         case 1:
            System.out.println("Quina poblacio vols esborrar?\n");
            System.out.println(s1.toString());							//Esborrem la poblacio que desitji
            String poblacio = teclat.nextLine();
            s1.esborrarP(poblacio);
            System.out.println(s1.toString());
            break;

         case 2:

            System.out.println("Quina comarca vols?\n");				//Demanem quina es la comarca que on vol buscar la primera activitat
            String comarca = teclat.nextLine();
            ActivitatCultural c = s1.Comarca(comarca);
            System.out.println(c + "\n\n\n");
            break;

         case 3:

            System.out.println(s1.mesDies());							//Printem l'activitat que porta mes dies en cartellera
            break;

         case 4:

            System.out.println("Quina provincia vols?\n");								//Escriu el numero d'activitats que es reprensenten en aquella provincia
            String provincia = teclat.nextLine();
            System.out.println("Activitats representades: " + s1.nActP(provincia));
            break;

         case 5:

            System.out.println("Quina poblacio vols ?\n");
            String poblacio2 = teclat.nextLine();

            System.out.println("Quina data inicial vols ?\n");						//demanem data i poblacio
            System.out.println("Quin dia vols ?\n");
            int dia = Integer.parseInt(teclat.nextLine());
            System.out.println("Quin mes vols ?\n");
            int mes = Integer.parseInt(teclat.nextLine());
            System.out.println("Quin any vols ?\n");
            int any = Integer.parseInt(teclat.nextLine());
            Data ini = new Data(dia, mes, any);

            System.out.println("Quina data final vols ?\n");
            System.out.println("Quin dia vols ?\n");
            int dia1 = Integer.parseInt(teclat.nextLine());
            System.out.println("Quin mes vols ?\n");
            int mes1 = Integer.parseInt(teclat.nextLine());
            System.out.println("Quin any vols ?\n");
            int any1 = Integer.parseInt(teclat.nextLine());
            Data fin = new Data(dia1, mes1, any1);
            if(s1.ActD(poblacio2, ini, fin).getnElem()==0) System.out.println("No hi ha cap activitat");
            else 
            System.out.println(s1.ActD(poblacio2, ini, fin).toString());			//printeja totes aquelles activitats que compleixen els requisits
            break;

         case 6:

            System.out.println("Quina categoria vols ?\n");
            String categoria = teclat.nextLine();
            System.out.println(s1.ActC(categoria).toString()); //Printeja les activitats que estan en aquella categoria
            break;

         case 7:
            System.out.println("Quina categoria vols ?\n");						//Demana categories i provincia
            String categoria1 = teclat.nextLine();
            System.out.println("Quina categoria2 vols ?\n");
            String categoria2 = teclat.nextLine();
            System.out.println("Quina provincia vols ?\n");
            String pronvicia2 = teclat.nextLine();
            LlistaActivitats s2 = s1.ActC(categoria2);					//Calcula quina categoria te mes representació
            LlistaActivitats s3 = s1.ActC(categoria1);
            int num2 = s2.nActP(pronvicia2);
            int num3 = s3.nActP(pronvicia2);
            if (num2 < num3) System.out.println("La categoria " + categoria1 + " te mes activitatats\n\n\n"); 				//Entrega el resultat
            else if (num2 > num3) System.out.println("La categoria " + categoria2 + " te mes activitatats\n\n\n");
            else System.out.println("Tenen el mateix nombre d'activitats\n\n\n");
            break;

         case 8:

            System.out.println(s1.toString()); //Printeja tota la llista
            break;

         case 9:
            System.out.println("Adeu bozo");			//Me despido de ti y me voy skrrt
            System.exit(1);

         }
         delay(4);		//Retardo per poder llegir la pantalla sense que sorti el menu
      }

   }

   private static int demanarNroLinies() {
      System.out.println("Indica el numero de linies a llegir del fitxer (maxim 3452)");
      int numLinies = Integer.parseInt(teclat.nextLine());
      if (numLinies < 0)
         numLinies = 1;
      if (numLinies > 3452)
         numLinies = 3452;
      return numLinies;
   }

   private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
      String[] result;

      result = new String[nLinies];
      Scanner f = new Scanner(new File("DadesActivitatsCulturalsMac.csv"));

      String capcalera = f.nextLine(); // la primera linia del fitxer es la capcalera, no conte dades de cap activitat
      //System.out.println("El format de les dades en cada linia es el seguent\n\n\t\t" + capcalera+"\n");
      for (int i = 0; i < nLinies; i++) {
         result[i] = f.nextLine();
      }
      f.close();
      return result;
   }

   public static void delay(int s) {
      try {
         Thread.sleep(s * 1000);
      } catch (InterruptedException ex) {
         Thread.currentThread().interrupt();
      }
   }

}