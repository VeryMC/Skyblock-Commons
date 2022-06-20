package main.java.fr.verymc.commons.enums;

public enum ServerType {

    HUB("skyblock_hub"),
    ISLAND("skyblock_iles"),
    DUNGEON("skyblock_dungeon");

    private String displayName;

    private ServerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}