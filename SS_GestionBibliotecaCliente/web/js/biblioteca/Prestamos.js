function agregarPrestamo() {
    document.getElementById("formularioPrestamo").reset();
    $('#modalPrestamo').modal('show');
}

function agregarLibro(codigo,nombres) {
    let ultimaFila = document.querySelector('#tablaAgregarLibro > tbody');
    const nuevoTr = document.createElement('tr');

    opcion="";
    for(i=0;i<codigo.length;i++){
        opcion+="<option value="+codigo[i]+">"+nombres[i]+"</option>";
    }
    
    let nuevoFormulario = `
    <td>
        <select id="libroPrestamo" name="libroPrestamo" class="form-control" required>
            <option value="">- Seleccionar -</option>`+opcion+`
        </select>
    </td>
    <td>
        <input type="number" value='0' min="0" id="cantidadPrestamo" name="cantidadPrestamo" class="form-control" required>
    </td>
    <td>
        <input type="date" id="fecEntPrestamo" name="fecEntPrestamo" class="form-control" required>
    </td>
    <td>
        <button type='button' class='btn btn-danger btnBorrar' onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode);"><i class='fas fa-trash'></i></button>
    </td>`;
    nuevoTr.innerHTML = nuevoFormulario;
    ultimaFila.appendChild(nuevoTr);
}