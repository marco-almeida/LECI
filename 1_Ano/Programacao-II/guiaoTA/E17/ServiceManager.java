
import static java.lang.System.*;

public class ServiceManager
{

  public ServiceManager(String [] serviceNames, int maxSize) {

    this.maxSize = maxSize;

    services = new ServiceQueue[serviceNames.length];

    for(int i=0; i<serviceNames.length; i++) {
      services[i] = new ServiceQueue(serviceNames[i], maxSize);
      services[i].beingServed = null;
    }
  }

  // verifica se o nome de um serviço é válido
  public boolean validServiceName(String serviceName) {
    int s = findService(serviceName);
    return s<services.length;
  }

  // verifica se existe um ou mais clientes à espera
  // de atendimento num dado serviço
  public boolean clientPending(String serviceName) {

    int s = findService(serviceName);
    assert s<services.length;

    return clientPending(s);
  }

  private boolean clientPending(int s) {
    return services[s].clientPendingExcept(beingServed());
  }

  // Verifica se a fila de um serviço está cheia
  public boolean serviceFull(String serviceName) {
    int s = findService(serviceName);
    assert s<services.length;

    return services[s].isFull();
  }

  // clear service manager
  public void clear() {
    for(int s=0; s<services.length; s++) {
      services[s].clear();
      services[s].beingServed = null;
    }
    first = null;
  }

  // retorna o índice do serviço no vector de serviços;
  // caso o serviço não exista, retorna o comprimento do vector
  private int findService(String serviceName) {

    int i = 0;
    while ( i<services.length  &&
        !services[i].serviceName().equals(serviceName) ) 
      i++;
    return i;
  }

  // Lista dos clientes que estão a ser servidos nos vários serviços
  private String [] beingServed() {
    int nBeingServed = 0;

    for(int i=0; i<services.length; i++)
      if (services[i].beingServed!=null) nBeingServed++;
    String [] a = new String[nBeingServed];
    int n = 0;
    for(int i=0; i<services.length; i++) {
      if (services[i].beingServed!=null) {
        a[n] = services[i].beingServed;
        n++;
      }
    }
    return a;
  }

  // Acrescentar dados de um serviço ao histórico de serviços
  private void logServiceData(int index, String client, int time) {
    HistoryNode n = new HistoryNode();
    n.time = time;
    n.serviceIndex = index;
    n.client = client;
    n.next = first;
    first = n;
  }

  // verifica se uma cadeia de caracteres existe num vector
  private static boolean inStringArray(String x, String [] a) {
    for(int i=0; i<a.length; i++)
      if (a[i].equals(x)) return true;
    return false;
  }

  // funde dois subvectores ordenados
  private static void mergeSubarrays(String[] a,int start,int middle,int end) {
    String[] b = new String[end-start];
    int i1 = start;
    int i2 = middle;
    int j = 0;
    while(i1 < middle && i2 < end) {
      if (a[i1].compareTo(a[i2]) < 0)
        b[j++] = a[i1++];
      else
        b[j++] = a[i2++];
    }
    while(i1 < middle)
      b[j++] = a[i1++];
    while(i2 < end)
      b[j++] = a[i2++];
    arraycopy(b, 0, a, start, end-start);
  }

  private ServiceQueue [] services;
  private int maxSize;

  private HistoryNode first = null;

  // ----------------- funções pedidas ------------------------

  //...

}

