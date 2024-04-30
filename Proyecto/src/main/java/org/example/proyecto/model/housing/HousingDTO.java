package org.example.proyecto.model.housing;

import java.util.Objects;

public class HousingDTO{
    private int id_alojamiento;
    private String nombre;
    private String calle;

    public HousingDTO(int id_alojamiento, String nombre, String calle) {
        this.id_alojamiento = id_alojamiento;
        this.nombre = nombre;
        this.calle = calle;
    }

    public int getId_alojamiento() {
        return id_alojamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingDTO that = (HousingDTO) o;
        return id_alojamiento == that.id_alojamiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_alojamiento);
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s",id_alojamiento,nombre,calle);
    }
}
