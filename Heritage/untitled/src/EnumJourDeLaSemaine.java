public enum EnumJourDeLaSemaine {
    LUNDI (false, ":("),
    MARDI (false, ":("),
    MERCREDI (false, ":("),
    JEUDI (false, ":("),
    VENDREDI (false, ":("),
    SAMEDI (true, ":)"),
    DIMANCHE (true, ":)");

    private boolean indiceWeekEnd; // si True jour de Week-end ; si False non jour de week-end
    private String smiley; // :) ou :(

    EnumJourDeLaSemaine(Boolean indiceWeekEnd, String smiley) {
        this.indiceWeekEnd = indiceWeekEnd;
        this.smiley = smiley;
    }

    public boolean getIndiceWeekEnd() {
        return this.indiceWeekEnd;
    }

    public String getSmiley() {
        return this.smiley;
    }


}
