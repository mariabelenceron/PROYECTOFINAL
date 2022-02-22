/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mabel
 */
public class Validacion {

    public Validacion() {
    }

    public boolean cedula(String value) {
        Pattern pat = Pattern.compile("^\\d+$");
        Matcher mat = pat.matcher(value);
        int suma = 0;
        if (value != null && mat.matches()) {
            if (value.length() == 9) {
                return false;
            } else {
                int a[] = new int[value.length() / 2];
                int b[] = new int[(value.length() / 2)];
                int c = 0;
                int d = 1;
                for (int i = 0; i < value.length() / 2; i++) {
                    a[i] = Integer.parseInt(String.valueOf(value.charAt(c)));
                    c = c + 2;
                    if (i < (value.length() / 2) - 1) {
                        b[i] = Integer.parseInt(String.valueOf(value.charAt(d)));
                        d = d + 2;
                    }
                }
                for (int i = 0; i < a.length; i++) {
                    a[i] = a[i] * 2;
                    if (a[i] > 9) {
                        a[i] = a[i] - 9;
                    }
                    suma = suma + a[i] + b[i];
                }
                int aux = suma / 10;
                int dec = (aux + 1) * 10;
                if ((dec - suma) == Integer.parseInt(String.valueOf(value.charAt(value.length() - 1)))) {
                    return true;
                } else if (suma % 10 == 0 && value.charAt(value.length() - 1) == '0') {
                    return true;
                } else {
                    return false;
                }
            }
        }else{
            return false;
        }
    }

    public boolean letras(String value) {
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ\\s]{1,40}$");
        Matcher mat = pat.matcher(value);
        int suma = 0;
        if (value != null && mat.matches() && value.trim().length()!=0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean correo(String value) {
        Pattern pat = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
        Matcher mat = pat.matcher(value);
        int suma = 0;
        if (value != null && mat.matches() && value.trim().length()!=0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean telefono(String value) {
        Pattern pat = Pattern.compile("^\\d{7,10}$");
        Matcher mat = pat.matcher(value);
        if (value != null && mat.matches() && value.trim().length()!=0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean numeros(String value) {
        Pattern pat = Pattern.compile("^\\d+$");
        Matcher mat = pat.matcher(value);
        if (value != null && mat.matches() && value.trim().length()!=0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean hora(String value) {
        Pattern pat = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])$");
        Matcher mat = pat.matcher(value);
        if (value != null && mat.matches() && value.trim().length()!=0) {
            return true;
        }else{
            return false;
        }
    }

    public boolean numerosLetras(String value) {
        Pattern pat = Pattern.compile("^[a-zA-Z0-9Ññ ]*$");
        Matcher mat = pat.matcher(value);
        if (value != null && mat.matches() && value.trim().length()!=0) {
            return true;
        }else{
            return false;
        }
    }
}
