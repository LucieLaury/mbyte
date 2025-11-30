package fr.jayblanc.mbyte.store.search;

import fr.jayblanc.mbyte.store.index.IndexStoreResult;

public class SearchResult {

    private String type;
    private String identifier;
    private String explain;
    private Object value;

    public SearchResult() {
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static SearchResult fromIndexStoreResult(IndexStoreResult result) {
        SearchResult searchResult = new SearchResult();
        searchResult.setIdentifier(result.getIdentifier());
        searchResult.setType(result.getType());
        searchResult.setExplain(result.getExplain());
        return searchResult;
    }
}
