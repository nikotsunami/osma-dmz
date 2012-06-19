package ch.unibas.medizin.osce.shared;

public enum WorkPermission {
	B(0),
	L(1),
	C(2);
	
	private final int key;
	
	private WorkPermission(int key){
		this.key = key;
	}
	
	public int getKey(){
	    return this.key;
	}
	
}
