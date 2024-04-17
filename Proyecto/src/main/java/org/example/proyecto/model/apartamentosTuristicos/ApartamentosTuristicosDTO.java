package org.example.proyecto.model.apartamentosTuristicos;

import org.example.proyecto.model.alojamientos.TipoAlojamiento;
import org.example.proyecto.model.alojamientos.AlojamientosDTO;

public class ApartamentosTuristicosDTO extends AlojamientosDTO {
    private float distanciaCentros;

    public ApartamentosTuristicosDTO(String codAlojamiento, String nombreAlojamiento, String direccionAlojamiento, int numeHuespedes, float distanciaCentros) {
        super(codAlojamiento, nombreAlojamiento, direccionAlojamiento, numeHuespedes, TipoAlojamiento.APARTAMENTOS_TURISTICOS);
        this.distanciaCentros = distanciaCentros;
    }
}
