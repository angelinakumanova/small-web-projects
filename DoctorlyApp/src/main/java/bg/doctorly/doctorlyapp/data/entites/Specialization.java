package bg.doctorly.doctorlyapp.data.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "specializations")
public class Specialization extends BaseEntity {
    @Column(nullable = false)
    private String name;

    public Specialization() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
