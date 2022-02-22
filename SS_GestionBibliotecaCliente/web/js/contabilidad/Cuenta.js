//------------- Variables
const formulario = document.querySelector('#formularioCuenta');
const inputs = document.querySelectorAll('#formularioCuenta input');
const btnRegistrar = document.querySelector('#btnRegistrar');
const btnCancelar = document.querySelector('#btnCancelar');

// Función para abrir el modal de agregar cuentas
function agregarCuenta() {
    document.getElementById("formularioCuenta").reset();
    $('#modalCuenta').modal('show');
}


/* =================================== VALIDACIONES ===================================*/
// -------------Variables
// Valores posibles a validar
const expresiones = {
    letras: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
    usuario: /^[a-zA-Z0-9Ññ ]*$/, // Letras, numeros, guion y guion_bajo
    password: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,}$/,
    correo: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
    telefono: /^\d{7,10}$/,
    numeros: /^\d+$/,
    fecha: /^(?:3[01]|[12][0-9]|0?[1-9])([\-/.])(0?[1-9]|1[1-2])\1\d{4}$/,
    numerosLetras: /^[a-zA-Z0-9Ññ ]*$/
}

// Inputs
const campos = {
    codigoCuenta: false,
    nombreCuenta: false,
    tipCueCuenta: false
}


document.addEventListener('DOMContentLoaded', bloquearBtnRegistrar);
btnCancelar.addEventListener('click', cancelarFormulario);

//Funcion para validar el formulario
const validarInputs = (e) => {
    switch (e.target.name) {
        case "codigoCuenta":
            validarCampo(expresiones.numerosLetras, e.target, 'codigoCuenta', 'Solo se admiten letras y números');
            break;
        case "nombreCuenta":
            validarCampo(expresiones.letras, e.target, 'nombreCuenta', 'Solo se admiten letras');
            break;
    }

    validarFormulario();
}

// Funcion que valida que todos los campos del formulario esten correctos
function validarFormulario() {
    if (campos.codigoCuenta && campos.nombreCuenta && campos.tipCueCuenta) {
        desbloquearBtnRegistrar();
    } else {
        bloquearBtnRegistrar();
    }
}

//Funcion para validar un campo
const validarCampo = (expresion, input, campo, mensaje) => {
    if (expresion.test(input.value) && input.value !== '') {
        document.getElementById(`grupo_${campo}`).classList.remove('formulario__grupo-incorrecto');
        document.getElementById(`grupo_${campo}`).classList.add('formulario__grupo-correcto');
        campos[campo] = true;
    } else {
        document.getElementById(`grupo_${campo}`).classList.add('formulario__grupo-incorrecto');
        document.getElementById(`grupo_${campo}`).classList.remove('formulario__grupo-correcto');
        imprimirAlerta(document.getElementById(`grupo_${campo}`), 'error', mensaje);
        campos[campo] = false;
    }
}

inputs.forEach((input) => {
    input.addEventListener('keyup', validarInputs);
    input.addEventListener('blur', validarInputs);
});

$(document).on('change', '#tipCueCuenta', function () {
    tipCueCuenta_select();
    campos.tipCueCuenta = true;
    validarFormulario();
});
function tipCueCuenta_select() {
    let tipCueCuentaSeleccionado = $('select[name="tipCueCuenta"] option:selected').val();
    return tipCueCuentaSeleccionado;
}

/* ------------------------------------------------------------- UI -------------------------------------------------------------*/
// Función para bloquear el boton de registrar, poner formatos
function bloquearBtnRegistrar() {
    $("#btnRegistrar").attr("disabled", "true");
    btnRegistrar.classList.remove('btn-primary');
    btnRegistrar.classList.add('btn-light');
}
// Función para desbloquear el boton de registrar, regresar formato inicial
function desbloquearBtnRegistrar() {
    $("#btnRegistrar").removeAttr("disabled")
    btnRegistrar.classList.remove('btn-light');
    btnRegistrar.classList.add('btn-primary');
}

// Función para imprimir una alerta
function imprimirAlerta(selector, tipo, mensaje) {
    const alerta = document.querySelector('.alerta');

    if (!alerta) {
        const divMensaje = document.createElement('div');
        divMensaje.classList.add('text-center', 'alert', 'd-block', 'col-auto', 'mt-1', 'alerta', 'p-1');

        //Agregar clase en base al tipo de error
        if (tipo === 'error') {
            divMensaje.classList.add('alert-danger');
        } else {
            divMensaje.classList.add('alert-success');
        }

        divMensaje.textContent = mensaje;

        selector.appendChild(divMensaje);
        setTimeout(() => {
            divMensaje.remove();
        }, 3000);
    }
}

function cancelarFormulario() {
    document.querySelectorAll('.formulario__grupo-correcto').forEach((clase) => {
        clase.classList.remove('formulario__grupo-correcto');
    });
    document.querySelectorAll('.formulario__grupo-incorrecto').forEach((clase) => {
        clase.classList.remove('formulario__grupo-incorrecto');
    });
    campos.codigoCuenta = false;
    campos.nombreCuenta = false;
    campos.tipCueCuenta = false;
}
