package br.com.rodrigocardoso.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * Created by rodri on 01/05/2018.
 */
public class Entidade {
    @Id()
    private Integer id;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "modified")
    private LocalDateTime modified;
    @Column(name = "active")
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public Entidade setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Entidade setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public Entidade setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public Entidade setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.getClass().getSimpleName() + ": ")
                .append("[ ");
        for (Field field : this.getClass().getFields()) {
            try {
                sb.append(field.getName() + ": " + field.get(this));
                sb.append(", ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
