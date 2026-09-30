package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T extends ICode> {
   private Map<String, T> elements = new HashMap();

   public Map<String, T> getElements() {
      return this.elements;
   }

   public List<T> getAllElements() {
      return new ArrayList(this.elements.values());
   }

   protected void addElement(T element) {
      this.elements.put(element.getCode(), element);
   }

   public T getElementCode(String code) {
      return this.elements.get(code);
   }
}
