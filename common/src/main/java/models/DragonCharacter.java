package models;

import java.io.Serializable;

public enum DragonCharacter implements Serializable {
    EVIL,
    CHAOTIC,
    CHAOTIC_EVIL;
    /**
     * @return перечисляет в строке все элементы Enum
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var forms : values()) {
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }
}