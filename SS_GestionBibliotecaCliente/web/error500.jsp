<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="../img/icono.png">
    <title>Error 500 - Error interno</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://unicons.iconscout.com/release/v2.1.9/css/unicons.css" />
    <style>
        :root {
            --bluePrimary: #0081A7;
            --whitePrimary: #E2F1FF;
            --textPrimary: 'Poppins', sans-serif;
        }

        body{
            font-family: var(--textPrimary);
            font-weight: 300;
            font-size: 15px;
            line-height: 1.7;
            color: var(--whitePrimary);
            background-color: var(--bluePrimary);
            overflow-x: hidden;
        }
        .section{
            position: relative;
            width: 100%;
            display: block;
        }
        .full-height{
            min-height: 100vh;
        }
        .icon-error {
            font-size: 400px;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row full-height justify-content-center">
            <div class="col-4 text-center align-self-center">
                <i class="uil uil-exclamation-triangle icon-error"></i>
            </div>
            <div class="col-8 text-center align-self-center py-5">
                <div class="section pb-5 pt-5 pt-sm-2 text-center">
                    <h1 class="mb-4 pb-3">
                        Se ha producido un Error!
                    </h1>
                    <p class="mb-4 pb-3">
                        El recurso no ha sido encontrado. Verifique su dirección url y vuelva a intentarlo.
                    </p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>