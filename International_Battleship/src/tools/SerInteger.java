package tools;

import java.io.Serializable;

public class SerInteger implements Serializable {
    private int val;

    public SerInteger (int val){
        this.val = val;
    }


    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }


    @Override
    public String toString(){
        return val+"";
    }
}
