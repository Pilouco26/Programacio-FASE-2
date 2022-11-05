package dades;

import java.util.Arrays;

public class LlistaActivitats {

   private ActivitatCultural[] llista;
   private int nElem;

   /**
    * Constructor de la llista d'activitats que rep la mida per parametre
    * @param mida
    */
   public LlistaActivitats(int mida) {
      nElem = 0;
      llista = new ActivitatCultural[mida];
   }

   /**
    * Copia la llista d'activitats
    * @return llista d'activitats
    */
   public LlistaActivitats copia() {

      LlistaActivitats nou = new LlistaActivitats(nElem);
      return (nou);
   }

   /**
    * Afegeix l'activitat que li passem per parametre si la llista no esta plena
    * @param a --> Activitat
    */
   public void afegirActivitat(ActivitatCultural a) {
      if (nElem < llista.length) {
         llista[nElem] = a.copia();
         nElem++;
      }

   }

   /**
    * Esborra les activitats que continguin el municipi que passem per parametre
    * @param m --> municipi
    */
   public void esborrarP(String m) {
      int i = 0;
      while (i < nElem) {
         if (llista[i].esTrobaEnAquestMunicipi(m)) {
            for (int j = i; j < nElem - 1; j++) {
               llista[j] = llista[j + 1];
            }

            nElem--;
         } else i++;
      }
   }

   /**
    * Busca i retorna la primera activitat que es troba en la comarca que passem per parametre
    * @param c --> comarca
    * @return Una copia de l'activitat cultural
    */
   public ActivitatCultural Comarca(String c) {
      boolean trobat = false;
      int i = 0;

      while (i < nElem && !trobat) {
         if (llista[i].esTrobaEnAquestaComarca(c)) trobat = true;
         else i++;
      }
      if (trobat) return llista[i].copia();
      else return null;
   }

   /**
    * Busca l'activitat cultural amb més dies en cartellera
    * @return Una copia de l'activitat cultural 
    */
   public ActivitatCultural mesDies() {
      int max = 0;
      int pos = 0;
      for (int i = 0; i < nElem; i++) {
         if (llista[i].numDiesEnCartell() > max) {
            max = llista[i].numDiesEnCartell();
            pos = i;
         }
      }

      return llista[pos].copia();
   }

   /**
    * Compta el numero d'activitats que es troben a la provincia que passem per parametre
    * @param p
    * @return
    */
   public int nActP(String p) {
      int num = 0;
      for (int i = 0; i < nElem; i++) {
         if (llista[i].esTrobaEnAquestaProvincia(p)) num++;
      }
      return num;
   }

   /**
    * Crea una subllista que compleix amb els requisits que passem per parametre
    * @param p --> Municipi
    * @param ini --> Data inicial
    * @param fin --> Data final
    * @return La subllista que compleix els requisits
    */
   public LlistaActivitats ActD(String p, Data ini, Data fin) {
      LlistaActivitats aux = new LlistaActivitats(nElem);
      for (int i = 0; i < nElem; i++) {
         if (llista[i].esTrobaEnAquestMunicipi(p) && ini.esDataInferiorOigual(llista[i].getDataIni()) && llista[i].getDataFi().esDataInferiorOigual(fin)) {
            aux.afegirActivitat(llista[i]);
         }
      }
      return aux;
   }

   /**
    * Crea subllista amb totes les activitats que estiguin a la categoria que passem per parametre
    * @param c --> categoria
    * @return Una copia de la subllista
    */
   public LlistaActivitats ActC(String c) {
      LlistaActivitats aux = new LlistaActivitats(nElem);
      for (int i = 0; i < nElem; i++) {
         if (llista[i].esAquestaCategoria(c)) aux.afegirActivitat(llista[i]);
      }
      return aux;

   }

   /**
    * Getter del nombre d'elements
    * @return nElem
    */
   public int getnElem() {
      return nElem;
   }

   /**
    * To string de la llista d'activitats culturals
    */
   public String toString() {

      return Arrays.toString(llista);
   }

}