package ar.com.up.theater.manager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_show")
public class ShowType {
    @Id
    @Column(name = "id_tipo_show")
    private Integer showTypeId;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    public ShowType() {
    }

    public ShowType(Integer showTypeId, String name, String description) {
        this.showTypeId = showTypeId;
        this.name = name;
        this.description = description;
    }

    public Integer getShowTypeId() {
        return showTypeId;
    }

    public void setShowTypeId(Integer showTypeId) {
        this.showTypeId = showTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
