/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author user
 */
public enum VrstaDogadjaja {
    
    
    KUCNA_ZURKA,
    KLUB_ZURKA,
    MATURSKO_VECE,
    TEMATSKA_ZURKA,
    KORPORATIVNA_ZURKA,
    OPEN_AIR_ZURKA,
    KONCERT_ZURKA,
    FESTIVAL;
       @Override
    public String toString() {
        // Pretvara nazive u čitkiji format
        return switch (this) {
            case KUCNA_ZURKA -> "Kućna žurka";
            case KLUB_ZURKA -> "Klub žurka";
            case MATURSKO_VECE -> "Maturko veče";
            case TEMATSKA_ZURKA -> "Tematska žurka";
            case KORPORATIVNA_ZURKA -> "Korporativna žurka";
            case OPEN_AIR_ZURKA -> "Open air žurka";
            case KONCERT_ZURKA -> "Koncert";
            case FESTIVAL -> "Festival";
        };
    }
    
    
}
