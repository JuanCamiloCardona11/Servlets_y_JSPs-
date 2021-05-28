/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global Document */

let $botonEnviar = document.getElementById("btn-comprar");

let $checkBoxFinCompra = document.getElementById("fin-compra");

$checkBoxFinCompra.addEventListener("click", function (e) {
    if ($checkBoxFinCompra.checked) {
        $botonEnviar.value = "Finalizar compra";
    } else {
        $botonEnviar.value = "Comprar producto";
    }    
});

$botonEnviar.addEventListener("click", function (e) {        
    if ($checkBoxFinCompra.checked) {
        alert("Gracias por su compra, se imprimirá su factura a continuación.");
    } else {
        alert("Producto agregado con éxito.");
    }
    return true;
});
