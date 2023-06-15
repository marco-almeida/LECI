class Type {
    private String typeName;

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            Type other = (Type) obj;
            return typeName.equals(other.typeName);
        }
        return false;
    }
}
