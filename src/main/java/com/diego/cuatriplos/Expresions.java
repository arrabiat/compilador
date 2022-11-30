/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.diego.cuatriplos;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pukdn
 */
public class Expresions {

    public static String postfijo(String expresion) {
        String ep = depurar(expresion);
        String[] array = ep.split(" ");

        // Declaración de las pilas
        Stack<String> E = new Stack<String>();
        // Pilaentrada

        Stack<String> P = new Stack<String>();
        // Pilatemporal para operadores

        Stack<String> S = new Stack<String>();

        // Pilasalida
        String numero1 = "";
        

        // Añadir la array a la Pila de entrada (E)
        for (int i = array.length - 1; i >= 0; i--) {
            E.push(array[i]);
        }

        // System.out.println("Operadores: " + operadores);
        try {

            // Algoritmo Infijo a Postfijo
            while (!E.isEmpty()) {
                // System.out.println(N.size());
                switch (pref(E.peek())) {

                    case 1:

                        P.push(E.pop());

                        break;

                    case 2:

                        while (!P.peek().equals("(")) {
                            S.push(P.pop());
                        }

                        P.pop();

                        E.pop();

                        break;
                    case 3:
                        if (P.empty()) {
                            try {
                                P.push(E.pop());
                                numero1 = E.pop();
                                if (E.peek().matches("[\\/\\*]")) {
                                    S.push(numero1);
                                    numero1 = "";
                                } else {
                                    S.push(numero1);
                                    if (P.peek().equals("-")) {
                                        S.push(P.pop());
                                    }
                                    // S.push(P.pop());
                                    numero1 = "";
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        } else {
                            P.push(E.pop());
                        }
                        break;

                    case 4:
                        P.push(E.pop());
                        S.push(E.pop());

                        while (!P.empty()) {
                            S.push(P.pop());
                        }

                        break;
                    default:
                        S.push(E.pop());
                        break;
                }
            }
            if (!numero1.equals("")) {
                S.push(numero1);
            }
            while (!P.empty()) {
                S.push(P.pop());
            }
            // Eliminacion de `impurezas´ en la expresionesalgebraicas

            String postfix = S.toString().replaceAll("[\\]\\[,]", "");

            return postfix;
        } catch (Exception ex) {
            System.out.println("Error en la expresión algebraica");

            System.err.println(ex);
            return null;
        }
    }

    public static String infijo(String expresion) {
        String expr = depurar(expresion);
        ArrayList<String> infijo = new ArrayList<String>();
        String regex = "[\\/\\-\\+\\*]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expr);
        String[] arrayInfix = expr.split(" ");
        int size;
        size = arrayInfix.length - 1;
        int operadores = 0;

        while (matcher.find()) {
            operadores++;
        }
        for (String a : arrayInfix) {
            infijo.add(a);
        }

        float res = 0;
        String operaciones = "";
        
        for (String a : infijo) {
            operaciones = (operaciones + " " + a).trim();
        }
        operaciones = operaciones + "\n";
        // System.out.println(operaciones);
        for (int i = 0; i < operadores; i++) {

            int OperaLV1 = indexOf(Pattern.compile("[\\/\\*]"), infijo);
            int OperaLV2 = indexOf(Pattern.compile("[\\+\\-]$"), infijo);
            if (OperaLV1 > 0) {
                if (infijo.get(OperaLV1).equals("/")) {
                    res = Float.parseFloat(infijo.get(OperaLV1 - 1)) / Float.parseFloat(infijo.get(OperaLV1 + 1));
                    infijo.set(OperaLV1, res + "");
                    infijo.remove(OperaLV1 + 1);
                    infijo.remove(OperaLV1 - 1);
                } else if (infijo.get(OperaLV1).equals("*")) {
                    res = Float.parseFloat(infijo.get(OperaLV1 - 1)) * Float.parseFloat(infijo.get(OperaLV1 + 1));
                    infijo.set(OperaLV1, res + "");
                    infijo.remove(OperaLV1 + 1);
                    infijo.remove(OperaLV1 - 1);
                }
            } else if (OperaLV2 > 0) {
                if (infijo.get(OperaLV2).equals("+")) {
                    res = Float.parseFloat(infijo.get(OperaLV2 - 1)) + Float.parseFloat(infijo.get(OperaLV2 + 1));
                    infijo.set(OperaLV2, res + "");
                    infijo.remove(OperaLV2 + 1);
                    infijo.remove(OperaLV2 - 1);
                } else if (infijo.get(OperaLV2).equals("-")) {
                    res = Float.parseFloat(infijo.get(OperaLV2 - 1)) - Float.parseFloat(infijo.get(OperaLV2 + 1));
                    infijo.set(OperaLV2, res + "");
                    infijo.remove(OperaLV2 + 1);
                    infijo.remove(OperaLV2 - 1);
                }
            }
            for (String a : infijo) {
                operaciones = (operaciones + " " + a).trim();
            }
            operaciones = operaciones + "\n";
            // System.out.println(operaciones);
        }
        return operaciones.trim();
    }

    public static int indexOf(Pattern pattern, ArrayList<String> s) {
        for (int i = 0; i < s.size(); i++) {
            String conincidencia = s.get(i);
            Matcher matcher = pattern.matcher(conincidencia);
            if (matcher.find()) {
                return i;
            }
        }
        return -1;
    }

    public static String depurar(String s) {

        s = s.replaceAll("\\s+", "");// Elimina espacios enblanco

        s = " " + s + " ";

        String simbols = "+-*/()";

        String str = "";

        // Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {

            if (simbols.contains("" + s.charAt(i))) {

                str += " " + s.charAt(i) + " ";

            } else {
                str += s.charAt(i);
            }

        }

        return str.replaceAll("\\s+", " ").trim();

    }

    private static int pref(String op) {

        int prf = 99;

        if (op.equals("*") || op.equals("/")) {
            prf = 4;
        }

        if (op.equals("+") || op.equals("-")) {
            prf = 3;
        }

        if (op.equals(")")) {
            prf = 2;
        }

        if (op.equals("(")) {
            prf = 1;
        }

        if (op.matches("\\d+")) {
            prf = 5;
        }

        return prf;
        // TODO code application logic here
    }
}
