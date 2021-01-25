<?php  
    $result = array();

    switch($_POST['op']) 
    {
        case '1':
            consultaGrupos();
            break;

        case '2':
            consultaGrupo();
            break;

        case '3':
            consultaAgrupamientos();
            break;

        case '4':
            consultaAlumno();
            break;

        case '5':
            consultaAlumnosD();
            break;

        case '6':
            consultaNotidicaciones();
            break;

        case '7':
            consultaNotidicacion();
            break;

        case '8':
            consultaNotificacionesIdUsuario();
            break;

        case '9':
            consultaUsuarioBC();
            break;
        
        default:
            # code...
            break;
    }

    function consultaGrupos()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['grupo'] = array();

        $select = "SELECT * FROM grupo";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idGrupo'] = $row['0'];
                $index['nombre'] = $row['1'];
                $index['descripcion'] = $row['2'];

                array_push($result['grupo'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-
    function consultaGrupo()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['grupo'] = array();

        $select = "SELECT * FROM grupo WHERE idGrupo='".$_POST['ID']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idGrupo'] = $row['0'];
                $index['nombre'] = $row['1'];
                $index['descripcion'] = $row['2'];

                array_push($result['grupo'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-
    function consultaAgrupamientos()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['agrupamiento'] = array();

        $select = "SELECT * FROM `agrupamiento` WHERE Grupo_idGrupo='".$_POST['ID']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idAgrupamiento'] = $row['0'];
                $index['Usuario_idUsuario'] = $row['1'];
                $index['Grupo_idGrupo'] = $row['2'];

                array_push($result['agrupamiento'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-
    function consultaAlumno()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['alumno'] = array();

        $select = "SELECT * FROM `usuario` WHERE idUsuario='".$_POST['ID']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idUsuario'] = $row['0'];
                $index['nombrecompleto'] = $row['1'];
                $index['boleta'] = $row['2'];
                $index['token'] = $row['3'];
                $index['tipo'] = $row['4'];
                $index['Programa_idPrograma'] = $row['5'];

                array_push($result['alumno'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-*OP 5*-
    function consultaAlumnosD()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['alumno'] = array();

        $select = "SELECT idUsuario FROM usuario EXCEPT(SELECT Usuario_idUsuario FROM agrupamiento WHERE Grupo_idGrupo = '".$_POST['ID']."')";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idUsuario'] = $row['0'];

                array_push($result['alumno'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-*OP 6*-
    function consultaNotidicaciones()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['notificacion'] = array();

        $select = "SELECT * FROM `notificacion`";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idNotificacion'] = $row['0'];
                $index['titulo'] = $row['1'];
                $index['descripcion'] = $row['2'];
                $index['fecha'] = $row['3'];
                $index['Grupo_idGrupo'] = $row['4'];

                array_push($result['notificacion'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-*OP 7*-
    function consultaNotidicacion()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['notificacion'] = array();

        $select = "SELECT * FROM `notificacion` WHERE idNotificacion=".$_POST['ID'];
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idNotificacion'] = $row['0'];
                $index['titulo'] = $row['1'];
                $index['descripcion'] = $row['2'];
                $index['fecha'] = $row['3'];
                $index['Grupo_idGrupo'] = $row['4'];

                array_push($result['notificacion'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-*OP 8*-
    function consultaNotificacionesIdUsuario()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['notificacion'] = array();

        $select = "SELECT idNotificacion,titulo FROM `notificacion` WHERE Grupo_idGrupo IN (SELECT Grupo_idGrupo FROM `agrupamiento` WHERE Usuario_idUsuario = '".$_POST['ID']."')";

        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idNotificacion'] = $row['0'];
                $index['titulo'] = $row['1'];

                array_push($result['notificacion'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }
    //*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-*OP 9*-
    function consultaUsuarioBC()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();
        $result['usuario'] = array();

        $select = "SELECT * FROM `usuario` WHERE boleta='".$_POST['BC']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            while($row = mysqli_fetch_array($mostrar))
            {
                $index['idUsuario'] = $row['0'];
                $index['nombrecompleto'] = $row['1'];
                $index['boleta'] = $row['2'];
                $index['token'] = $row['3'];
                $index['tipo'] = $row['4'];
                $index['Programa_idPrograma'] = $row['5'];

                array_push($result['usuario'], $index);
            }

            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }


?>