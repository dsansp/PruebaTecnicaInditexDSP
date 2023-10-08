package Model;

public enum DataModelEnum {
	
	URL_PROJECT("url");

	DataModelEnum(String key){
		this.key = key;
	}
	
	private final String key;
	
	public final String getKey() {
		return key;
	}
}
