package model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("08f132d1-827b-4017-8d24-cdf2f7da5633")
public abstract class SpecialAction implements SpecialActionInterface {
    @objid ("326a4d34-3f42-4376-ae5a-f78d0d9c6ca8")
    private String cost;

    @objid ("0a8597d3-c402-4a39-868d-bcfc70f576b1")
    public String getCost() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.cost;
    }

    @objid ("18914bda-ce6b-4628-aaea-629988076c0a")
    public void setCost(final String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.cost = value;
    }

    @objid ("a468340f-b143-465a-ade8-cc1850be0ecf")
    public abstract void doAction();

}
