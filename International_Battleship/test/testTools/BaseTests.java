package testTools;

public abstract class BaseTests {
    protected TestObjectsGenerator objGenerator;

    /**
     * This class is here to instantiate in one place every tool classes we need for tests
     */
    public BaseTests(){
        this.objGenerator = new TestObjectsGenerator();
    }

}
