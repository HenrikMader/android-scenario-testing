package utils.entities;

public class OCShare {

    private String id;
    private String owner;
    private String type;
    private String itemName;
    private String shareeName;

    public OCShare(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getShareeName() {
        return shareeName;
    }

    public void setShareeName(String shareeName) {
        this.shareeName = shareeName;
    }

    public boolean hasPassword() {
        return type.equals("3") && shareeName != "" && shareeName != null;
    }
}
