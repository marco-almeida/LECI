public class Room {
    private Point baixoEsquerda;
    private Point cimaDireita;
    private String divisao;

    public Room(Point cimaDireita, Point baixoEsquerda, String divisao){
        this.baixoEsquerda = baixoEsquerda;
        this.cimaDireita = cimaDireita;
        this.divisao = divisao;
    }

    public String roomType(){
        return this.divisao;
    }

    public Point bottomLeft(){
        return this.baixoEsquerda;
    }

    public Point topRight(){
        return this.cimaDireita;
    }

    public Point geomCenter(){
        return new Point((this.cimaDireita.x()+bottomLeft().x())/2,(this.cimaDireita.y()+bottomLeft().y())/2);
    }

    public double area(){
        double width = this.cimaDireita.x() - this.baixoEsquerda.x();
        double length = this.cimaDireita.y() - this.baixoEsquerda.y();
        return width * length;
    }

}
