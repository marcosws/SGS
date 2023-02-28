/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sp.view.mdi;

/**
 *
 * @author Marcos
 */
public class PrincipalSize {
    
    private static double width;
    private static double height;

    public static double getWidth() {
        return width;
    }
    public static void setWidth(double width) {
        PrincipalSize.width = Math.abs(width - 18);
    }
    public static double getHeight() {
        return height;
    }
    public static void setHeight(double height) {
        PrincipalSize.height = Math.abs(height - 82);
    }
    
}
