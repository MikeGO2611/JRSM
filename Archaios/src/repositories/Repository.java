package commons.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T extends ICode> {
   //Mapa clave valor de los elementos del repositorio.
   private Map<String, T> elements = new HashMap<String,T>();

   public Map<String, T> getElements() {
      return this.elements;
   }

   /**
    * Getter de todos los elementos.
    * @return Devuelve todos los valores de la lista elementos.
    */
   public List<T> getAllElements() {
      return new ArrayList<T>(this.elements.values());
   }

   /**
    * Añade un elemento a la lista.
    * @param element Elemento a introducir.
    */
   public void addElement(T element) {
      this.elements.put(element.getCode(), element);
   }

   /**
    * Getter del elemento dado un código.
    * @param code Código especificado
    * @return Elemento buscado.
    */
   public T getElementCode(String code) {
      return this.elements.get(code);
   }
}
