package org.example.proyecto.model.housing;

<<<<<<< HEAD

public class HousingDTO extends Hotel_apsDTO {
    private int HousingId;
=======
import java.util.Objects;

public class HousingDTO{
    /*attributes*/
    private int id_alojamiento;
    private String nombre;
    private String calle;
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651

    /**
     * HousingDTO builder
     * @param id_alojamiento id
     * @param nombre name
     * @param calle address
     */
    public HousingDTO(int id_alojamiento, String nombre, String calle) {
        this.id_alojamiento = id_alojamiento;
        this.nombre = nombre;
        this.calle = calle;
    }
    /*getters*/
    public int getId_alojamiento() {
        return HousingId;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return String.format("%d,%d,%s,%s,%s",HousingId,super.getId(), super.getNombre(), super.getDireccion(), super.getTipo());
=======
    public String getNombre() {
        return nombre;
>>>>>>> a3722196b5bd945b50e0c8b9c391e23138175651
    }

    public String getCalle() {
        return calle;
    }

    /*setters*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * equals
     * @param o -> housing id
     * @return true if both of the housing compared have the same id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingDTO that = (HousingDTO) o;
        return id_alojamiento == that.id_alojamiento;
    }

    /**
     * hashCode
     * @return hashCode associated with the id
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_alojamiento);
    }

}
