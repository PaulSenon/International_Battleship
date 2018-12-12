package tools;

public class UniqueIdGenerator {
    protected static int id;
    protected static UniqueIdGenerator instance;

    public static UniqueIdGenerator newInstance() {
        if (instance == null)
            instance = new UniqueIdGenerator(0);
        return instance;
    }

    private UniqueIdGenerator(int id){
        UniqueIdGenerator.id = 0;
    }

    public static int getNextId(){
        UniqueIdGenerator.id++;
        return UniqueIdGenerator.id;
    }
}
