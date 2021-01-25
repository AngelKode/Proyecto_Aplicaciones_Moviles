<?php  
    $result = array();

    switch($_POST['op']) 
    {
        case '1':
            eliminarGrupo();
            break;

        case '2':
            eliminarAgrupamientoIDUsuario();
            break;

        case '3':
            eliminarAgrupamientoIDGrupo();
            break;

        case '4':
            eliminarNotificacion();
            break;

        default:
            # code...
            break;
    }

    function eliminarGrupo()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();

        $select = "DELETE FROM `grupo` WHERE idGrupo = '".$_POST['id']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-
    function eliminarAgrupamientoIDUsuario()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();

        $select = "DELETE FROM `agrupamiento` WHERE Usuario_idUsuario='".$_POST['id']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-
    function eliminarAgrupamientoIDGrupo()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();

        $select = "DELETE FROM `agrupamiento` WHERE Grupo_idGrupo='".$_POST['id']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
            $result["success"]="1";
        }
        else
        {
            $result["success"]="0";
        }
        
        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-*OP 4*-
    function eliminarNotificacion()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $result = array();

        $select = "DELETE FROM `notificacion` WHERE Grupo_idGrupo='".$_POST['id']."'";
        $mostrar = mysqli_query($conexion,$select);

        if ($mostrar) 
        {
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