package tools;

import java.util.Calendar;

public class DataCustom {

    public String mostrarDataEHora(){
        Calendar c = Calendar.getInstance();

        String hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
        String minuto = Integer.toString(c.get(Calendar.MINUTE));

        String dia = Integer.toString(c.get(Calendar.DAY_OF_MONTH));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String ano = Integer.toString(c.get(Calendar.YEAR));

        String dataAtual = dia+"/"+mes+"/"+ano+" "+hora+":"+minuto;

        return dataAtual;

    }

}