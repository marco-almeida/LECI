package PooRecurso2022_23;

import java.time.LocalDate;

public class DigitalObject {
    private LocalDate creationDate;
    private String doi;
    private SensitivityLevel sensitivity;
    private boolean isPublic;
    private User owner;

    public DigitalObject(String doi, User owner) {
        this.doi = doi;
        this.owner = owner;
        this.sensitivity = SensitivityLevel.PERSONAL;
        this.isPublic = false;
        this.creationDate = LocalDate.now();
    }

    public DigitalObject(String doi, User owner, SensitivityLevel sensitivity) {
        this(doi, owner);
        this.sensitivity = sensitivity;
        this.isPublic = false;
    }

    public User getOwner() {
        return owner;
    }

    public void setSensitivity(SensitivityLevel sensitivity) {
        this.sensitivity = sensitivity;
    }

    public SensitivityLevel getSensitivity() {
        return sensitivity;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isPublic() {
        return isPublic;
    }

    @Override
    public String toString() {
        return String.format("doi: %s; Created: %s; Level: %s%s",
                doi,
                creationDate.toString(),
                sensitivity,
                isPublic ? "; Public" : "");
    }
}
