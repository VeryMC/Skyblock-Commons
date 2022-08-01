package main.java.fr.verymc.commons.enums;

public enum ServerType {

    SKYBLOCK_HUB("skyblock_hub"),
    SKYBLOCK_ISLAND("skyblock_iles"),
    SKYBLOCk_DUNGEON("skyblock_dungeon"),
    HUB("hub"),
    COMBOFFA("comboffa"),
    LIMBO("limbo");

    private String displayName;

    private ServerType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}