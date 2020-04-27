package it.project.server.privileges.enums;

import java.util.HashSet;
import java.util.Set;

public enum Sex {
	M("M", "Maschio"),
	F("F", "Femmina"),
	NA("NA", "Preferisco non rispondere");
	
	private final String code;
	private final String description;
	
	
	private final static Set<String> values = new HashSet<String>(Sex.values().length);

    static{
        for(Sex f: Sex.values())
            values.add(f.name());
    }

    public static boolean contains( String value ){
        return values.contains(value);
    }
	
	private Sex(String code, String description){
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
}