//------------- Variables
const formulario = document.querySelector('#formularioRegistrarUsuario');
const inputs = document.querySelectorAll('#formularioRegistrarUsuario input');
const btnRegistrar = document.querySelector('#btnRegistrar');
const btnCancelar = document.querySelector('#btnCancelar');

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
    hora: /^([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])$/,
    numerosLetras: /^[a-zA-Z0-9Ññ ]*$/
}
function cedula_val(value) {
    let [suma, mul, index] = [0, 1, value.length];
    while (index--) {
        let num = value[index] * mul;
        suma += num - (num > 9) * 9;
        mul = 1 << index % 2;
    }

    if ((suma % 10 === 0) && (suma > 0)) {
        return true;
    } else {
        return false;
    }
}

// Inputs
const campos = {
    codigoUsuario: false,
    cedulaUsuario: false,
    nombreUsuario: false,
    correoUsuario: false,
    sesionDesdeUsuario: false,
    sesionHastaUsuario: false,
}


document.addEventListener('DOMContentLoaded', bloquearBtnRegistrar);
btnCancelar.addEventListener('click', cancelarFormulario);

//Funcion para validar el formulario
const validarInputs = (e) => {
    switch (e.target.name) {
        case "codigoUsuario":
            validarCampo(expresiones.numerosLetras, e.target, 'codigoUsuario', 'Solo se admiten letras y números');
            break;
        case "cedulaUsuario":
            if (cedula_val(e.target.value)) {
                document.getElementById(`grupo_cedulaUsuario`).classList.remove('formulario__grupo-incorrecto');
                document.getElementById(`grupo_cedulaUsuario`).classList.add('formulario__grupo-correcto');
                campos['cedulaUsuario'] = true;
            } else {
                document.getElementById(`grupo_cedulaUsuario`).classList.add('formulario__grupo-incorrecto');
                document.getElementById(`grupo_cedulaUsuario`).classList.remove('formulario__grupo-correcto');
                imprimirAlerta(document.getElementById(`grupo_cedulaUsuario`), 'error', 'Cedula Invalida');
                campos['cedulaUsuario'] = false;
            }
        case "nombreUsuario":
            validarCampo(expresiones.letras, e.target, 'nombreUsuario', 'Solo se admiten letras');
            break;
        case "correoUsuario":
            validarCampo(expresiones.correo, e.target, 'correoUsuario', 'Email invalido');
            break;
        case "sesionDesdeUsuario":
            validarCampo(expresiones.hora, e.target, 'sesionDesdeUsuario', 'Ingrese en el formato establecido');
            break;
        case "sesionHastaUsuario":
            validarCampo(expresiones.hora, e.target, 'sesionHastaUsuario', 'Ingrese en el formato establecido');
            break;
    }

    validarFormulario();
}

// Funcion que valida que todos los campos del formulario esten correctos
function validarFormulario() {
    if (campos.codigoUsuario && campos.cedulaUsuario && campos.nombreUsuario && campos.correoUsuario && campos.sesionDesdeUsuario && campos.sesionHastaUsuario) {
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
}
