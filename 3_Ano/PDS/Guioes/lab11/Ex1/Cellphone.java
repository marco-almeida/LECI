package lab11.Ex1;

public class Cellphone {
    String processor;
    double price;
    int memory;
    String camera;

    public Cellphone(String processor, double price, int memory, String camera){
        this.processor = processor;
        this.price = price;
        this.memory = memory;
        this.camera = camera;
    }

    @Override
    public String toString(){
        return "Processor: " + processor;
    }

}
