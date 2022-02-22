//------------- Variables
const formulario = document.querySelector('#login_form');
const inputs = document.querySelectorAll('#login_form input');
const btnRegistrar = document.querySelector('#btnRegistrar');
const btnVerificar = document.querySelector('#btnVerificar');


document.addEventListener('DOMContentLoaded', bloquearBtnRegistrar);
btnVerificar.addEventListener('click', validarCaptcha);

function validarCaptcha() {
    const response = grecaptcha.getResponse();
    if (response) {
        desbloquearBtnRegistrar();
    } else {
        error.innerHTML = "Debe chequear el captcha";
        bloquearBtnRegistrar();
    }
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