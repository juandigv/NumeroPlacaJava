package jd.code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;

public class Placa extends JFrame{

    private JLabel etiqueta;
    private JTextField campo;
    private JLabel diahoy;
    private JButton verificar;
    private JDialog dialogo;
    private JLabel etiqueta2;

    public Placa(){
        super("Circulacion Placas");
        Calendar calendar = Calendar.getInstance();
        String [] hoy = new String[]{
                "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        diahoy = new JLabel("Hoy es :  "+hoy[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "   ", JLabel.CENTER);
        etiqueta = new JLabel("Placa: ");
        campo = new JTextField(5);
        verificar = new JButton("Verificar");
        etiqueta2 = new JLabel();
        dialogo = new JDialog(this);
        dialogo.setSize(300,100);
        FlowLayout f = new FlowLayout();
        setLayout(f);
        add(diahoy);
        add(etiqueta);
        add(campo);
        add(verificar);

        verificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                verificarPlaca();
            }
        });
    }

    private void verificarPlaca(){
        String placa = campo.getText();
        int PlacaNum = getParteNumerica(placa);
        int PlacaDig = PlacaNum%10;

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day){

            case Calendar.MONDAY:
                verificarPlaca(PlacaDig, 0,1);
                break;
            case Calendar.TUESDAY:
                verificarPlaca(PlacaDig, 2,3);
                break;

            case Calendar.WEDNESDAY:
                verificarPlaca(PlacaDig, 4, 5);
                break;

            case Calendar.THURSDAY:
                verificarPlaca(PlacaDig, 6,7);
                break;

            case Calendar.FRIDAY:
                verificarPlaca(PlacaDig, 8,9);
                break;
        }
        dialogo.add(etiqueta2);
        dialogo.setVisible(true);

    }

    int getParteNumerica(String placa){
        int res=0;
        for(int i=0; i<placa.length(); i++){
            char c = placa.charAt(i);
            if (c>'0' && c<='9'){
                res=res*10 + Character.getNumericValue(c);
            }
        }
        return res;
    }

    public void verificarPlaca(int nPlaca,int No1, int No2){
        if (nPlaca==No1|| nPlaca==No2)
            etiqueta2.setText("Su placa no puede circular");
        else
            etiqueta2.setText("Su placa puede circular con normalidad");
    }



}
