/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.cuatriplos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
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
        tabla.addColumn("Numero 1");
        tabla.addColumn("Numero 2");
        tabla.addColumn("Operador");
        tabla.addColumn("Resultado");
        String[] arrayInfix = expresion.split(" ");
        Stack<String> Entrada = new Stack<String>();
        Stack<String> Numeros = new Stack<String>();

        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            Entrada.push(arrayInfix[i]);
        }

        while (!Entrada.empty()) {
            String[] vector = new String[4];
            System.out.println(Arrays.asList(Entrada));
            Thread.sleep(500);
            if (!Entrada.peek().matches("[-\\+\\*\\/]")) {
                Numeros.push(Entrada.pop());
                System.out.println(Arrays.asList(Numeros));
            } else if (Entrada.peek().matches("[-\\+\\*\\/]")) {
                if (Entrada.peek().equals("/")) {
//                    Entrada.pop();
                    float num1 = Float.parseFloat(Numeros.pop());
                    float num2 = Float.parseFloat(Numeros.pop());
                    float res = num2 / num1;
                    vector[0] = num1 + "";
                    vector[1] = num2 + "";
                    vector[2] = Entrada.peek();
                    vector[3] = res + "";
                    Numeros.push(res + "");
                    System.out.println(Arrays.asList(Numeros));
                } else if (Entrada.peek().equals("*")) {
//                    Entrada.pop();
                    float num1 = Float.parseFloat(Numeros.pop());
                    float num2 = Float.parseFloat(Numeros.pop());
                    float res = num2 * num1;
                    vector[0] = num1 + "";
                    vector[1] = num2 + "";
                    vector[2] = Entrada.peek();
                    vector[3] = res + "";
                    Numeros.push(res + "");
                    System.out.println(Arrays.asList(Numeros));
                } else if (Entrada.peek().equals("-")) {
                    // Signos.push(Entrada.pop());
//                    Entrada.pop();
                    float num1 = Float.parseFloat(Numeros.pop());
                    float num2 = Float.parseFloat(Numeros.pop());
                    float res = num2 - num1;
                    vector[0] = num1 + "";
                    vector[1] = num2 + "";
                    vector[2] = Entrada.peek();
                    vector[3] = res + "";
                    Numeros.push(res + "");
                    System.out.println(Arrays.asList(Numeros));
                } else if (Entrada.peek().equals("+")) {
                    // Signos.push(Entrada.pop());
//                    Entrada.pop();
                    float num1 = Float.parseFloat(Numeros.pop());
                    float num2 = Float.parseFloat(Numeros.pop());
                    float res = num2 + num1;
                    vector[0] = num1 + "";
                    vector[1] = num2 + "";
                    vector[2] = Entrada.peek();
                    vector[3] = res + "";
                    Numeros.push(res + "");
                    System.out.println(Arrays.asList(Numeros));
                }
                tabla.addRow(vector);
                Entrada.pop();
            }
        }
        return tabla;
    }

}
