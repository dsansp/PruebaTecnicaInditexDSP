package Model;


public enum EnvDataEnum {
	

	ENV_DATA_PATH("env.data.path");





	EnvDataEnum(String key){
		this.key = key;
	}
	
	private final String key;
	
	public final String getKey() {
		return key;
	}
}
