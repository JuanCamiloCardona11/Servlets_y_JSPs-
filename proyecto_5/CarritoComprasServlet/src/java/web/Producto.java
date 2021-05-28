/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

/**
 *
 * @author JuanCamiloC
 */
class Producto {
    private String nombre;
    private float precio;
    private int cantComprado;

    public Producto() {
    }   
    
    public Producto(String nombre, float precio, int cantComprado) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantComprado = cantComprado;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantComprado() {
        return this.cantComprado;
    }       

    public void setCantComprado(int cantComprado) {
        this.cantComprado = cantComprado;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nPrecio: " + precio + "\nCantidad: " + cantComprado;
    }
    
    
    
}
