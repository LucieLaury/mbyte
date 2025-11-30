package fr.jayblanc.mbyte.store.index;

import java.io.Serializable;

public class IndexStoreResult implements Serializable {

    private String type;
    private String identifier;
    private float score;
    private String explain;

    public IndexStoreResult() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

}
