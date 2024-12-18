package martinezruiz.javier.pmdmtarea02.models;

/**
 * POJO que sirve como modelo para los datos que manejamos
 */
public class Card {

    public Card(int imgId, String name, String description, String skill) {
        this.imgId = imgId;
        this.name = name;
        this.description = description;
        this.skill = skill;
    }

    public Card(int imgId, String name) {
        this.imgId = imgId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


    private String name;
    private int imgId;
    private String description;
    private String skill;
}