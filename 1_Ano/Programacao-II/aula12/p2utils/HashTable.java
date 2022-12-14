package p2utils;

public class HashTable<E> {

  private KeyValueList<E>[] array;
  private int size = 0;

  /**
   * {@code HashTable} constructor.
   * 
   * @param dim The dimension of the table
   */
  @SuppressWarnings("unchecked")
  public HashTable(int dim) {
    assert dim > 0;
    int len = dim; // should be the nearest prime number
    array = (KeyValueList<E>[]) new KeyValueList[len];
    clear();
  }

  /**
   * Updates the element associated to the given key, if the key already exists;
   * otherwise, inserts a new pair with the given key and element.
   * 
   * @param key  a key
   * @param elem an element
   * @return {@code true} if the key was added (size increased), otherwise
   *         {@code false}.
   */
  public boolean set(String key, E elem) {
    int pos = hashFcn(key);
    boolean newelem = array[pos].set(key, elem);
    if (newelem)
      size++;

    assert contains(key) && get(key).equals(elem);
    return newelem;
  }

  /**
   * Returns the element associated to the given key.
   * 
   * @param key A key
   * @return The element associated to the given key
   */
  public E get(String key) {
    assert contains(key);

    int pos = hashFcn(key);
    return array[pos].get(key);
  }

  /**
   * Removes the given key and associated element.
   * 
   * @param key a key
   */
  public void remove(String key) {
    assert contains(key);

    int pos = hashFcn(key);
    array[pos].remove(key);
    size--;

    assert !contains(key);
  }

  /**
   * Checks if the given key exists in the list.
   * 
   * @param key a key
   * @return {@code true} if the key exists and {@code false} otherwise
   */
  public boolean contains(String key) {
    int pos = hashFcn(key);
    return array[pos].contains(key);
  }

  /**
   * Checks if the table is empty.
   * 
   * @return {@code true} if table empty, otherwise {@code false}.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of elements in the table.
   * 
   * @return Number of elements in the table
   */
  public int size() {
    return size;
  }

  /**
   * Clear the table.
   */
  public void clear() {
    for (int i = 0; i < array.length; i++)
      array[i] = new KeyValueList<E>();
    size = 0;
  }

  // Devolve o ??ndice correspondente ?? chave str.
  // (?? o hashCode da str "enrolado" modulo N, onde N=array.length.)
  private int hashFcn(String str) {
    int result = str.hashCode() % array.length;
    if (result < 0)
      result += array.length;
    assert 0 <= result && result < array.length; // p??s-condi????o
    return result;
  }

  // fazer aqui as fun????es pedidas no gui??o...
  public String[] keys() {
    String[] arr = new String[size];
    int i = 0;
    for (KeyValueList<E> linha : array) {

      if (!linha.isEmpty()) {
        String[] ooo = linha.keys();
        if (linha.size() > 1) {
          for (String key : ooo) {
            arr[i++] = key;
          }
        } else {
          arr[i] = ooo[0];
          i++;
        }

      }
    }
    return arr;

  }

  public String toString() {
    return toString("[", " : ", "]");
  }

  public String toString(String left, String sep, String right) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");

    for (KeyValueList<E> lis : array) {
      if (!lis.isEmpty()) {
        sb.append(lis.toString(left, sep, right));
        sb.append(" ");
      }
    }

    // sb.setLength(sb.length() - 2);// virgula e espa??ogungauuignugnaingiunugina
    sb.append("}");

    return sb.toString();
  }

}
