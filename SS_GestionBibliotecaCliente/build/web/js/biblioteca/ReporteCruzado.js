/* global Console */

document.addEventListener("DOMContentLoaded", ()=>{
    const boton = document.querySelector("#btnCrearPdf");
    const impresionCompleta = document.querySelector("#contenedorPadre");
    /* eslint no-console: "error" */
    boton.addEventListener("click", ()=>{
        const elementoParaConvertir = impresionCompleta;
        html2pdf()
            .set({
                margin:1,
                filename: 'ReporteCruzado.pdf',
                image:{
                    type:'jpeg',
                    quality: 0.98
                },
                html2canvas:{
                    scale: 3,
                    letterRendering: true
                },
                jsPDF:{
                    unit: "in",
                    format: "a3",
                    orientation: 'portrait'
                }
            })
            .from(elementoParaConvertir)
            .save()
            .catch(Console.error("Existe un error"));
        }); 
});

