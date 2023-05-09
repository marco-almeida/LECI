package lab09.Ex02;

abstract class Chef {
    private Chef successor = null;

    public Chef setSuccessor(Chef successor) {
        this.successor = successor;
        return this;
    }

    public void cook(String food) {
        if (successor != null) {
            successor.cook(food);
        } else {
            System.out.println("We're sorry but that request can't be satisfied by our service!");
        }
    }

    protected boolean canHandleFood(String food, String format) {
        return (food == null) || (food.toLowerCase().contains(format.toLowerCase()));
    }

}
