function agregarComprobante() {
    //document.querySelector('#codigoComprobante').disabled = true;
    document.getElementById("formularioComprobante").reset();
    $('#modalComprobante').modal('show');
}

function agregarAgregarDetCom(codigo,nombres) {
    let ultimaFila = document.querySelector('#tablaAgregarDetCom > tbody');
    const nuevoTr = document.createElement('tr');
    
    opcion="";
    for(i=0;i<codigo.length;i++){
        opcion+="<option value="+codigo[i]+">"+nombres[i]+"</option>";
    }
    let nuevoFormulario = `
    <tr>
        <td>
            <select id="cuentaComprobante" name="cuentaComprobante" class="form-control" required>
                <option value="">- Seleccionar -</option>`+opcion+`
            </select>
        </td>
        <td>
            <input type="number" value='0.00' min="0" id="cDebeComprobante" name="cDebeComprobante" class="form-control" required>
        </td>
        <td>
            <input type="number" value='0.00' min="0" id="cHaberComprobante" name="cHaberComprobante" class="form-control" required>
        </td>
        <td>
            <button type='button' class='btn btn-danger btnBorrar' onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);"><i class='fas fa-trash'></i></button>
        </td>
    </tr>`;
    nuevoTr.innerHTML = nuevoFormulario;
    ultimaFila.appendChild(nuevoTr);
}