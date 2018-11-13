package Model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("5385df1e-d6e5-4755-95ec-ee612f03941b")
public class Square {
    @objid ("525c233d-9c03-4afe-976b-b19a32ff7072")
    private boolean isInternational;

    @objid ("eedf956e-a75c-4f0e-958c-d4ed046590fb")
    private boolean isPort;

    @objid ("36a823ea-0db8-4dfc-b6e6-4f3944a7a965")
    private boolean isMined;
    
    public boolean isDestroyed;

    @objid ("7fc5366a-c5b7-439b-9a40-3f26a1f0e737")
    private String isOccupied;

    @objid ("157c8670-ab36-4666-af76-1603e97c0a54")
    public Coord coord;

    @objid ("71fb2926-4073-4c66-972d-b3ffa8bfd7ad")
    public void isMined() {
    }

    @objid ("ecce4821-e089-4299-ad70-a2569ecba1d9")
    public void isInternational() {
    }

    @objid ("90caa4e6-5e25-4fed-b63b-28c8ff945c6c")
    public void isPort() {
    }

    @objid ("365015f1-ad33-4b07-8d75-308e7c87d61e")
    public Square() {
    }

	public void destroy() {
		this.isDestroyed = true;
	}
    
}
