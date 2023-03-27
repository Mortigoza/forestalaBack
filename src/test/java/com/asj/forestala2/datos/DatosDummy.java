package com.asj.forestala2.datos;

import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.PlantacionParticular;
import com.asj.forestala2.negocio.mapper.PlantacionesParticularesMapper;

public class DatosDummy {
    public static Persona getPersonaAxel(){
        return new Persona(0,"Axel", "Colotta", "tu vieja",
                "1033", "Llavallol", "1530478566", null );
    }

    public static Persona getPersonaDavid(){
        return new Persona(0, "David", "Villareal", "lalala",
                "667", "Lomas", "7878787878", null);
    }




}
