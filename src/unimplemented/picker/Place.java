package unimplemented.picker;


public class Place {
	
	private int id;
	private String name;
	private String address;
	private String town;
	private String type;
	
	private static int staticID = 0;
	
	public Place(String name, String address, String town, String type) {
		id = staticID++;
		this.name = name;
		this.address = address;
		this.town = town;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", address=" + address + ", town=" + town + ", type=" + type
				+ "]";
	}

}
