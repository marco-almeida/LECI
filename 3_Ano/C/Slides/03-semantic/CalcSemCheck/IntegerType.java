public class IntegerType extends Type {
   public IntegerType() {
      super("integer");
   }

   @Override public boolean subtype(Type other) {
      return super.subtype(other) || other.name().equals("real");
   }

}

