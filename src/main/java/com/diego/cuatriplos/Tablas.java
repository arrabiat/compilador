/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.cuatriplos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author pukdn
 */
public class Tablas {

    public static TableModel operadores(String operacion) {
        DefaultTableModel tabla = new DefaultTableModel() {
        };
        ArrayList<String> operadores = new ArrayList();
        String[] listaoperadores = operacion.split("[0-9\\.]+");
        // operadores.addAll(Arrays.asList(listaoperadores));
        for (String a : listaoperadores) {
            if (a.equals("+")) {
                operadores.add("+ (suma)");
            } else if (a.equals("-")) {
                operadores.add("- (resta)");
            } else if (a.equals("*")) {
                operadores.add("* (multiplicacion)");
            } else if (a.equals("/")) {
                operadores.add("/ (division)");
            } else {
                operadores.add(" ");
            }
        }
        ArrayList<Float> numeros = new ArrayList();
        String[] listanumeros = operacion.split("[\\+\\-\\*\\/]");
        for (String a : listanumeros) {
            numeros.add(Float.valueOf(a));
        }
        tabla.addColumn("Numeros");
        tabla.addColumn("Operadores");
        for (int i = 0; i < listaoperadores.length; i++) {
            String[] vector = {numeros.get(i).toString(), operadores.get(i)};
            tabla.addRow(vector);
        }
        return tabla;
    }

    public static TableModel cuatriplos(String expresion) throws InterruptedException {
        DefaultTableModel tabla = new DefaultTableModel() {
        };
        tabla.addColumn("Operador");
        tabla.addColumn("Numero 1");
        tabla.addColumn("Numero 2");
        tabla.addColumn("Resultado");
        String[] arrayInfix = expresion.split(" ");
        Stack<String> Entrada = new Stack<String>();
        Stack<String> Numeros = new Stack<String>();

        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            Entrada.push(arrayInfix[i]);
        }
        int count = 0;
        while (!Entrada.empty()) {
            String[] vector = new String[4];
            System.out.println(Arrays.asList(Entrada));
//            Thread.sleep(2000);
            if (!Entrada.peek().matches("[-\\+\\*\\/]")) {
                Numeros.push(Entrada.pop());
                System.out.println(Arrays.asList(Numeros));
            } else if (Entrada.peek().matches("[-\\+\\*\\/]")) {
                final String regex = "(R\\d)\\s=\\s(.+)";
                float num1, num2, res;
                count++;
                String operador = Entrada.pop();
                vector[0] = operador;
                String n1 = Numeros.pop();
                String n2 = Numeros.pop();
                if (n1.matches(regex)) {
                    String[] groups = stractPattern(n1);
                    vector[1] = groups[0];
                    num1 = Float.parseFloat(groups[1]);
                } else {
                    num1 = Float.parseFloat(n1);
                    vector[1] = n1;
                }
                if (n2.matches(regex)) {
                    String[] groups = stractPattern(n2);
                    vector[2] = groups[0];
                    num2 = Float.parseFloat(groups[1]);
                } else {
                    num2 = Float.parseFloat(n2);
                    vector[2] = n2;
                }
                if (operador.equals("/")) {
                    res = num2 / num1;
                    vector[3] = "R" + count + " = " + res + "";
                    Numeros.push("R" + count + " = " + res + "");
                    System.out.println(Arrays.asList(Numeros));
                } else if (operador.equals("*")) {

                    res = num2 * num1;
                    vector[3] = "R" + count + " = " + res + "";
                    Numeros.push("R" + count + " = " + res + "");
                    System.out.println(Arrays.asList(Numeros));
                    
                } else if (operador.equals("-")) {
                    // Signos.push(Entrada.pop());
//                    Entrada.pop();
                    res = num2 - num1;
                    vector[3] = "R" + count + " = " + res + "";
                    Numeros.push("R" + count + " = " + res + "");
                    System.out.println(Arrays.asList(Numeros));
                } else if (operador.equals("+")) {
                    // Signos.push(Entrada.pop());
//                    Entrada.pop();
                    res = num2 + num1;
                    vector[3] = "R" + count + " = " + res + "";
                    Numeros.push("R" + count + " = " + res + "");
                    System.out.println(Arrays.asList(Numeros));
                }
                tabla.addRow(vector);
            }
        }
        return tabla;
    }

    public static String[] stractPattern(String string) {
        final String regex = "(R\\d)\\s=\\s(.+)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(string);
        matcher.find();
        String[] groups = {matcher.group(1), matcher.group(2)};
        return groups;
    }

}
