package fr.jayblanc.mbyte.store.api.dto;

import fr.jayblanc.mbyte.store.files.entity.Node;

import java.util.Date;

public class NodeDto {

    private boolean root;
    private Node.Type type;
    private String id;
    private String parent;
    private String name;
    private String mimetype;
    private long size;
    private Date creation;
    private Date modification;

    public NodeDto() {
    }

    public static NodeDto fromNode(Node node) {
        NodeDto dto = new NodeDto();
        dto.root = node.isRoot();
        dto.type = node.getType();
        dto.id = node.getId();
        dto.name = node.getName();
        dto.mimetype = node.getMimetype();
        dto.size = node.getSize();
        dto.creation = new Date(node.getCreation());
        dto.modification = new Date(node.getModification());
        return dto;
    }

    public Node.Type getType() {
        return type;
    }

    public void setType(Node.Type type) {
        this.type = type;
    }

    public boolean isRoot() {
        return root;
    }

    public boolean isFolder() {
        return type == Node.Type.TREE;
    }

    public boolean isFile() {
        return type == Node.Type.BLOB;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getModification() {
        return modification;
    }

    public void setModification(Date modification) {
        this.modification = modification;
    }
}
