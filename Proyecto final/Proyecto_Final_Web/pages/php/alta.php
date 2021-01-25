<?php  
    $result = array();

    switch($_POST['op']) 
    {
        case '1':
            altaGrupo();
            break;

        case '2':
            altaAgrupamiento();
            break;

        case '3':
            altaNotificacion();
            break;
        
        default:
            # code...
            break;
    }

    function altaGrupo()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $insertInto = "INSERT INTO `grupo`(`nombre`, `descripcion`) VALUES ('".$_POST['nombre']."','".$_POST['descricion']."')";

        $resultSQL = mysqli_query($conexion,$insertInto);

        if ($resultSQL)
        {
            $result["success"]="1";
            $result["idIngreso"] = mysqli_insert_id($conexion);
        }
        else
        {
            $result["success"]="0";
        }

        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }

    //*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-*OP 2*-
    function altaAgrupamiento()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $insertInto = "INSERT INTO `agrupamiento`(`Usuario_idUsuario`, `Grupo_idGrupo`) VALUES ('".$_POST['IDUSU']."','".$_POST['IDGRU']."')";

        $resultSQL = mysqli_query($conexion,$insertInto);

        if ($resultSQL)
        {
            $result["success"]="1";
            $result["idIngreso"] = mysqli_insert_id($conexion);
        }
        else
        {
            $result["success"]="0";
        }

        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }
	
    //*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-*OP 3*-
    function altaNotificacion()
    {
        $conexion = new mysqli("localhost","root","","mydb");

        $insertInto = "INSERT INTO `notificacion`(`titulo`, `descripcion`, `fecha`, `Grupo_idGrupo`) VALUES ('".$_POST['TIT']."','".$_POST['DES']."','".$_POST['FEC']."','".$_POST['IDGRU']."')";

        $resultSQL = mysqli_query($conexion,$insertInto);

        if ($resultSQL)
        {
            $result["success"]="1";
            $result["idIngreso"] = mysqli_insert_id($conexion);
        }
        else
        {
            $result["success"]="0";
        }

        echo json_encode($result, JSON_UNESCAPED_UNICODE);
        mysqli_close($conexion);
    }
?>