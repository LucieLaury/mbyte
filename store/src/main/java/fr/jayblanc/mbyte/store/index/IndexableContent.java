package fr.jayblanc.mbyte.store.index;

public class IndexableContent {

    private String type;
    private String identifier;
    private String content;
    private Scope scope;

    public IndexableContent() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public enum Scope {
        PUBLIC,
        PRIVATE
    }
}
