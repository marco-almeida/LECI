public class House {
    private String tipoRoom;
    private Room[] rooms;
    private int addDivs;

    public House(String tipoRoom){
        this.tipoRoom = tipoRoom;
        rooms = new Room[8];
        addDivs = 4;
    }

    public House(String tipoRoom, int numRooms, int addDivs){
        this.tipoRoom = tipoRoom;
        rooms = new Room [numRooms];
        this.addDivs = addDivs;
    }

    public void addRoom(Room newRoom){
        
    }
}
